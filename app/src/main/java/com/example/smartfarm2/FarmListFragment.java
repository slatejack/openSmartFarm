package com.example.smartfarm2;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.smartfarm2.Adapter.FarmListAdapter;
import com.example.smartfarm2.DataBase.DBOpenHelper;
import com.example.smartfarm2.DataBase.Farm;
import com.example.smartfarm2.DataBase.Weather;
import com.example.smartfarm2.Manager.MqttMangerSW;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jaeger.library.StatusBarUtil;
import com.kongzue.dialog.v3.TipDialog;
import com.kongzue.dialog.v3.WaitDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FarmListFragment extends Fragment {
    private static final String TAG="天气请求";
    public static final String TAG1 = FarmListFragment.class.getSimpleName();

    private String city_code="101270101";//获取天气信息的城市代码，此处暂时固定为成都

    private DBOpenHelper mDBOpenHelper;//天气数据库操作类
    private DBOpenHelper mDBOpenHelper1;//农场信息数据库操作类
    private ArrayList<Weather> data;//天气数据库数据
    private ArrayList<Farm> data1;//农场数据库数据

    FarmListAdapter farmListAdapter;

    private ArrayList<String> list1=new ArrayList<String>();//农场名称
    private ArrayList<String> list2=new ArrayList<String>();//农场英文名
    private ArrayList<String> list3=new ArrayList<String>();//农场温度

    int clickchance;//点击次数

    Thread thread;
    private MqttMangerSW mqttMangerSW;//MQTT管理类

    private ConstraintLayout bg_weather;
    private TextView tv_weathername;
    private TextView tv_wenhouyu;
    private ImageView icon_weather;
    private ListView farmlist;

    public Handler handler;
    public Handler handler1;//Mqtt信息接收handler

    String obj="";//获取传输数据类型
    int sensorNum=0;//获取传感器个数
    String sensorName="";//传感器名称
    int sensorID=0;//传感器ID号
    String zhuangtai;//农场状态

    int Temp1=0;//温度1
    int Temp2=0;//温度2
    int Temp3=0;//温度3
    int Temp4=0;//温度4
    int Temp5=0;//温度5


    int airHumidity1=0;//空气湿度1
    int airHumidity2=0;//空气湿度2

    int lightIntensity=0;//光照强度
    int CO2=0;//二氧化碳浓度

    int soilMoisture1=0;//土壤湿度1
    int soilMoisture2=0;//土壤湿度2
    int soilMoisture3=0;//土壤湿度3

    int conductivity=0;//电导率
    int soilSalinity=0;//盐分



    public FarmListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View txview=inflater.inflate(R.layout.fragment_farm_list, container, false);

        //初始化数据库
        mDBOpenHelper = new DBOpenHelper(getContext());
        mDBOpenHelper.getWritableDatabase();
        mDBOpenHelper1 = new DBOpenHelper(getContext());
        mDBOpenHelper1.getWritableDatabase();

        tv_weathername=txview.findViewById(R.id.tv_weathername);
        tv_wenhouyu=txview.findViewById(R.id.tv_wenhou);
        icon_weather=txview.findViewById(R.id.icon_weather);
        bg_weather=txview.findViewById(R.id.bg_weather);
        tv_weathername.setTextColor(getResources().getColor(R.color.weathername));
        tv_wenhouyu.setTextColor(getResources().getColor(R.color.weathername));
        farmlist=txview.findViewById(R.id.lv_farmlist);

        Bundle bundle=getArguments();
        clickchance=bundle.getInt("点击次数");


        farmListAdapter=new FarmListAdapter(getContext(),list1,list2,list3);
        farmlist.setAdapter(farmListAdapter);
        farmlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startNewIntent(position);
            }
        });

        //如果用户是第一次点击
        if (clickchance==0){
            mDBOpenHelper.deleteAll();//第一次启动时先全部清空天气数据库数据集
            sendRequestWithOkHttp1();//只当第一次点击时请求天气信息
        }

        //如果用户不再是第一次点击（直接从本地数据库中获取天气数据）
        else if (clickchance>0){
            data=mDBOpenHelper.getAllData();//获取数据库中全部信息
            parseJSONWithJSONObject(data.get(0).getWeatherinfo());//获取第一条的天气json并发送给对应方法解析
        }
        //sendRequestWithMQTT();
        parseJSONWithJSONObject2("{\"Obj\":\"Sens\",\"Num\":3,\"Payload\":[{\"ID\":1,\"Type\":1,\"Data\":[12,-5,11]},{\"ID\":3,\"Type\":3,\"Data\":[31,28]},{\"ID\":4,\"Type\":3,\"Data\":[32,24]}],\"Time\":[2019,5,31, 13,22,57]}");
        return txview;
    }




    /**
     * okhttp发送数据至服务器请求天气情况
     * 注意：最好不要直接请求天气信息，最好由服务器请求此接口，再由移动端去请求服务器
     * 服务器请求次数最好为一天一次，不用多次重复请求
     */
    private void sendRequestWithOkHttp1() {
        WaitDialog.show((AppCompatActivity)getActivity(),"请稍后", TipDialog.TYPE.SUCCESS)
                .setTipTime(2000);
        new Thread( new Runnable() {
            @Override
            public void run() {
                JSONObject obj = new JSONObject();
                Log.i(TAG, "发送数据");
                MediaType type = MediaType.parse( "application/json" );
                RequestBody RequestBody2 = RequestBody.create( type, obj.toString() );
                try {

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url( "http://t.weather.itboy.net/api/weather/city/"+city_code )
                            .get()
                            .build();
                    Response response = client.newCall( request ).execute();
                    String responseData = response.body().string();
                    Log.i(TAG, "接受到数据："+responseData);
                    Message m = new Message();
                    m.obj = responseData;
                    handler.sendMessage(m);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i(TAG, "错误信息："+e);
                }
            }
        } ).start();
    }

    /**
     * okhttp对返回的数据进行解析
     * @param jsonData
     * @return
     */
    public String parseJSONWithJSONObject(String jsonData) {
        int status=0;//监测获取状态，200为正常，403表示参数错误，404表示参数数位不对
        String weatherName="";
        String wenhou="";
        try {
            JSONObject object=new JSONObject(jsonData);
            status=object.getInt("status");
            switch (status){
                case 200://如果返回值正常则进行下一步解析
                    JSONObject object1=object.getJSONObject("data");//获取data JsonObject对象
                    JSONArray jsonArray=object1.getJSONArray("forecast");//获取data内的forecast数组
                    JSONObject object2=new JSONObject(jsonArray.get(0).toString());//将forecast数组中的第一个jsonObject对象抽出来
                    weatherName=object2.getString("type");//获取到天气情况
                    wenhou=object2.getString("notice");//获取问候语
                    tv_wenhouyu.setText(wenhou);

                    //如果是用户第一次点击，则将接受到的信息放入本地数据库中
                    if (clickchance==0)
                    mDBOpenHelper.add(jsonData,weatherName,wenhou);

                    if (weatherName.contains("晴")) {
                        tv_weathername.setText("天气晴朗");
                        icon_weather.setImageResource(R.drawable.icon_sun);
                        bg_weather.setBackgroundColor(getResources().getColor(R.color.bg_sun));
                        changecolor(getResources().getColor(R.color.bg_sun));
                    }

                    else if (weatherName.contains("多云")) {
                        tv_weathername.setText("天气多云");
                        icon_weather.setImageResource(R.drawable.icon_cloud);
                        bg_weather.setBackgroundColor(getResources().getColor(R.color.bg_cloud));
                        changecolor(getResources().getColor(R.color.bg_cloud));
                    }


                    else if (weatherName.contains("沙")||weatherName.contains("尘")){
                        tv_weathername.setText("当心风沙");
                        icon_weather.setImageResource(R.drawable.icon_dust);
                        bg_weather.setBackgroundColor(getResources().getColor(R.color.bg_dust));
                        changecolor(getResources().getColor(R.color.bg_dust));
                    }


                    else if (weatherName.contains("雾")||weatherName.contains("霾")){
                        tv_weathername.setText("雾霾天气");
                        icon_weather.setImageResource(R.drawable.icon_haze);
                        bg_weather.setBackgroundColor(getResources().getColor(R.color.bg_cloud));
                        changecolor(getResources().getColor(R.color.bg_cloud));
                    }


                    else if (weatherName.contains("阴")){
                        tv_weathername.setText("天气阴沉");
                        icon_weather.setImageResource(R.drawable.icon_overcast);
                        bg_weather.setBackgroundColor(getResources().getColor(R.color.bg_overcast));
                        changecolor(getResources().getColor(R.color.bg_overcast));
                    }


                    else if (weatherName.contains("雨")){
                        tv_weathername.setText("今日有雨");
                        icon_weather.setImageResource(R.drawable.icon_rain);
                        bg_weather.setBackgroundColor(getResources().getColor(R.color.bg_rain));
                        changecolor(getResources().getColor(R.color.bg_rain));
                    }

                    else if (weatherName.contains("雪")){
                        tv_weathername.setText("今日有雪");
                        icon_weather.setImageResource(R.drawable.icon_snow);
                    }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }


    /**
     * MQTT发送数据至服务器请求农场情况
     */
    private void sendRequestWithMQTT() {
       thread=new Thread( new Runnable() {
            @Override
            public void run() {
                //连接mqtt服务器
                mqttMangerSW = new MqttMangerSW( "tcp://118.24.19.135:1883", getContext(), handler1 );
                mqttMangerSW.connect();

            }
        } );
       thread.start();
    }

    /**
     * 对返回的农场json数据进行解析
     * @param jsonData
     * @return
     */
    public String parseJSONWithJSONObject2(String jsonData) {
        JSONObject object1;//传感器obj
        try {
            JSONObject object=new JSONObject(jsonData);
            obj=object.getString("Obj");
                sensorNum=object.getInt("Num");
                JSONArray jsonArray=object.getJSONArray("Payload");
                for (int i=0;i<sensorNum-1;i++){
                    object1=new JSONObject(jsonArray.get(i).toString());
                    sensorID=object1.getInt("ID");
                    switch (sensorID){
                        case 1:
                            sensorName="光照温湿度变送器";
                            Temp1=Integer.valueOf(object1.getJSONArray("Data").get(0).toString());//获取温度1的值
                            airHumidity1=Integer.valueOf(object1.getJSONArray("Data").get(1).toString());//获取空气湿度1的值
                            lightIntensity=Integer.valueOf(object1.getJSONArray("Data").get(2).toString());//获取光照强度的值
                            break;
                        case 2:
                            sensorName="co2温湿度变送器";
                            Temp2=Integer.valueOf(object1.getJSONArray("Data").get(0).toString());//获取温度二的值
                            airHumidity2=Integer.valueOf(object1.getJSONArray("Data").get(1).toString());//获取空气湿度1的值
                            CO2=Integer.valueOf(object1.getJSONArray("Data").get(2).toString());//获取二氧化碳浓度值
                            break;
                        case 3:
                            sensorName="土壤水分传感器1";
                            Temp3=Integer.valueOf(object1.getJSONArray("Data").get(0).toString());
                            soilMoisture1=Integer.valueOf(object1.getJSONArray("Data").get(1).toString());
                            break;
                        case 4:
                            sensorName="土壤水分传感器2";
                            Temp4=Integer.valueOf(object1.getJSONArray("Data").get(0).toString());
                            soilMoisture2=Integer.valueOf(object1.getJSONArray("Data").get(1).toString());
                            break;
                        case 5:
                            sensorName="土壤水分传感器3";
                            Temp5=Integer.valueOf(object1.getJSONArray("Data").get(0).toString());
                            soilMoisture3=Integer.valueOf(object1.getJSONArray("Data").get(1).toString());
                            break;
                        case 10:
                            sensorName="土壤检测传感器";
                            conductivity=Integer.valueOf(object1.getJSONArray("Data").get(0).toString());
                            soilSalinity=Integer.valueOf(object1.getJSONArray("Data").get(1).toString());
                    }
                }

                mDBOpenHelper1.deleteAll1();//先清空当前数据库中的数据
                mDBOpenHelper1.add1(Temp1,Temp2,Temp3,Temp4,Temp5,soilMoisture1,soilMoisture2,soilMoisture3,airHumidity1,airHumidity2,lightIntensity,CO2,conductivity,soilSalinity);//将新获取到的数据传入数据库
                data1=mDBOpenHelper1.getAllData1();//再获取当前数据库中的数据

                //数据全部统一为数据库中的数据
                Temp1=data1.get(0).getTemp1();
                Temp2=data1.get(0).getTemp2();
                Temp3=data1.get(0).getTemp3();
                Temp4=data1.get(0).getTemp4();
                Temp5=data1.get(0).getTemp5();
                soilMoisture1=data1.get(0).getSoilMoisture1();
                soilMoisture2=data1.get(0).getSoilMoisture2();
                soilMoisture3=data1.get(0).getSoilMoisture3();
                airHumidity1=data1.get(0).getAirHumidity1();
                airHumidity2=data1.get(0).getAirHumidity2();
                lightIntensity=data1.get(0).getLightIntensity();
                CO2=data1.get(0).getCO2();
                conductivity=data1.get(0).getConductivity();
                soilSalinity=data1.get(0).getSoilSalinity();

                if (Temp1>=50|Temp2>=50|Temp3>=50|Temp4>=50|Temp5>=50){
                    //初始化农场列表
                    list1.add("大棚一");
                    list2.add("FarmLand 1");
                    list3.add("温度过高");
                    zhuangtai="温度过高";
                    farmListAdapter.notifyDataSetChanged();
                    farmlist.setAdapter(farmListAdapter);
                }
                else {
                    //初始化农场列表
                    list1.add("大棚一");
                    list2.add("FarmLand 1");
                    list3.add("正常");
                    zhuangtai="正常";
                    farmListAdapter.notifyDataSetChanged();
                    farmlist.setAdapter(farmListAdapter);
                }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }


    public void startNewIntent(int position){
        Intent intent=new Intent(getActivity(),FarmDetailsNew.class);//农场详情界面做好后记得改！
        intent.putExtra("农场状态",zhuangtai);//传递农场状态便于下一界面改变ui
        intent.putExtra("农场名称",list1.get(position));
        intent.putExtra("farmname",list2.get(position));
        /*
        intent.putExtra("温度1",Temp1);
        intent.putExtra("温度2",Temp2);
        intent.putExtra("温度3",Temp3);
        intent.putExtra("温度4",Temp4);
        intent.putExtra("温度5",Temp5);
        intent.putExtra("土壤湿度1",soilMoisture1);
        intent.putExtra("土壤湿度2",soilMoisture2);
        intent.putExtra("土壤湿度3",soilMoisture3);
        intent.putExtra("空气湿度1",airHumidity1);
        intent.putExtra("空气湿度2",airHumidity2);
        intent.putExtra("光照强度",lightIntensity);
        intent.putExtra("CO2浓度",CO2);
        intent.putExtra("电导率",conductivity);
        intent.putExtra("盐分",soilSalinity);
        */
        intent.putExtra("光照强度",lightIntensity);
        intent.putExtra("pm2.5",10.3);
        intent.putExtra("风速",20.1);
        intent.putExtra("风向","东南风");
        intent.putExtra("雨量",31.5);
        startActivity(intent);
    }

    //改变状态栏颜色
    private void changecolor(int color){
        Window window =getActivity().getWindow();
        //After LOLLIPOP not translucent status bar
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Then call setStatusBarColor.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //动画资源依赖
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(
                    TransitionInflater.from(getContext())
                            .inflateTransition(android.R.transition.move));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                parseJSONWithJSONObject(msg.obj.toString());
                WaitDialog.dismiss();
            }
        };
        handler1=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                parseJSONWithJSONObject2(msg.obj.toString());
            }
        };
    }

    public static FarmListFragment newInstance() {
        return new FarmListFragment();
    }

    //界面切换动画使用处
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ConstraintLayout bg_weather = (ConstraintLayout)getView().findViewById(R.id.bg_weather);
        final TextView tv_weathername=(TextView)getView().findViewById(R.id.tv_weathername);
        final TextView tv_wenhou=(TextView)getView().findViewById(R.id.tv_wenhou);


        getActivity().findViewById(R.id.navigation_dashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment equipmentFragment=getFragmentManager().findFragmentByTag(TAG1);

                if (equipmentFragment==null)
                    equipmentFragment=EquipmentFragment.newInstance();

                    getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(bg_weather, ViewCompat.getTransitionName(bg_weather))
                        .addSharedElement(tv_weathername,ViewCompat.getTransitionName(tv_weathername))
                        .addSharedElement(tv_wenhou,ViewCompat.getTransitionName(tv_wenhou))
                        .addToBackStack(TAG1)
                        .replace(R.id.ViewFragment, equipmentFragment)
                        .commit();

            }
        });

        getActivity().findViewById(R.id.navigation_notifications).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutFragment aboutFragment=new AboutFragment();
                //aboutFragment.setArguments(getIntent().getExtras());
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ViewFragment,aboutFragment)
                        .commit();
            }
        });
    }
}
