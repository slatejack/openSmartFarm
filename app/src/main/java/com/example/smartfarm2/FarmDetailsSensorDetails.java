package com.example.smartfarm2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.smartfarm2.Adapter.SensorListAdapter;
import com.example.smartfarm2.Manager.MqttMangerSW;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FarmDetailsSensorDetails extends AppCompatActivity {
    private ListView sensorlist;

    Thread thread;
    private MqttMangerSW mqttMangerSW;//MQTT管理类
    public Handler handler1;//Mqtt信息接收handler
    String sensorName;//收到传感器名称
    String sensorValue;//收到的传感器数据值
    private ArrayList<String> list1=new ArrayList<String>();//传感器名称
    private ArrayList<String> list2=new ArrayList<String>();//传感器值
    private SensorListAdapter sensorListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_details_sensor_details);
        init();
        getIntentMessage();
        changecolor(Color.WHITE);
        sensorListAdapter=new SensorListAdapter(getApplicationContext(),list1,list2);
        sensorlist.setAdapter(sensorListAdapter);
        parseJSONWithJSONObject2("");
    }

    //初始化控件
    private void init(){
        sensorlist=findViewById(R.id.lv_farm_details_sensor_details);
    }
    //接受前一界面传输回来的值
    private void getIntentMessage(){
        sensorName=getIntent().getStringExtra("sensorName");
    }

    /**
     * MQTT发送数据至服务器请求农场情况
     */
    private void sendRequestWithMQTT() {
        thread=new Thread( new Runnable() {
            @Override
            public void run() {
                //连接mqtt服务器
                mqttMangerSW = new MqttMangerSW( "tcp://118.24.19.135:1883", getApplicationContext(), handler1 );
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
        int sensorNum=0;//传感器数量
        sensorValue="";//传感器值
        JSONObject object1;//传感器obj

        /*
        此处对数据进行解析
        */

        sensorNum=3;//获取到的传感器数量的值
        for (int i=0;i<sensorNum;i++){
            //此处取i对应的传感器值并把值赋予sensorValue变量
            sensorValue="67";//此处为假象固定传值，后期逻辑写入后记得改正
            list1.add(sensorName);
            list2.add(sensorValue);
            sensorListAdapter.notifyDataSetChanged();
            sensorlist.setAdapter(sensorListAdapter);
        }

        return jsonData;
    }

    //改变状态栏颜色
    private void changecolor(int color){
        Window window =getWindow();
        //After LOLLIPOP not translucent status bar
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Then call setStatusBarColor.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }
}
