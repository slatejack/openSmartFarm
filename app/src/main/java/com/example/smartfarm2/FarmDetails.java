package com.example.smartfarm2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;

public class FarmDetails extends AppCompatActivity {

    String farmCNName;//农场中文名
    String farmENName;//农场英文名
    String farmdetails;//农场状态

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

    //设备按钮当前状态
    int shebei1State=0;
    int shebei2State=0;
    int shebei3State=0;
    int shebei4State=0;
    int shebei5State=0;

    TextView tv_cn_farm_detail_name;//农场中文名
    TextView tv_en_farm_detail_name;//农场英文名

    TextView tv_lightIntensity;//光照强度
    TextView tv_temp1;//温度1
    TextView tv_temp2;//温度2
    TextView tv_temp3;//温度3
    TextView tv_temp4;//温度4
    TextView tv_temp5;//温度5
    TextView tv_co2;//CO2浓度
    TextView tv_conductivity;//电导率
    TextView tv_soilMoisture1;//土壤湿度1
    TextView tv_soilMoisture2;//土壤湿度2
    TextView tv_soilMoisture3;//土壤湿度3
    TextView tv_airHumidity1;//空气湿度1
    TextView tv_airHumidity2;//空气湿度2

    //控制器开关
    CardView shebei1;
    CardView shebei2;
    CardView shebei3;
    CardView shebei4;
    CardView shebei5;
    TextView tv_shebei1;
    TextView tv_shebei2;
    TextView tv_shebei3;
    TextView tv_shebei4;
    TextView tv_shebei5;
    TextView tv_shebei1_zhuangtai;
    TextView tv_shebei2_zhuangtai;
    TextView tv_shebei3_zhuangtai;
    TextView tv_shebei4_zhuangtai;
    TextView tv_shebei5_zhuangtai;
    ImageView icon_shebei1;
    ImageView icon_shebei2;
    ImageView icon_shebei3;
    ImageView icon_shebei4;
    ImageView icon_shebei5;
    ConstraintLayout cl_shebei1;
    ConstraintLayout cl_shebei2;
    ConstraintLayout cl_shebei3;
    ConstraintLayout cl_shebei4;
    ConstraintLayout cl_shebei5;
    BubbleSeekBar sk_shebri4;
    BubbleSeekBar sk_shebri5;

    ImageView icon_farm_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_details);

        init();
        getIntentfromfragment();
        assignment();
        onclicked();
        onseekbar();

    }

    //接受前一界面传递过来的数据
    public void getIntentfromfragment(){
        farmCNName=getIntent().getStringExtra("农场名称");
        farmENName=getIntent().getStringExtra("farmname");
        farmdetails=getIntent().getStringExtra("农场状态");
        Temp1=getIntent().getIntExtra("温度1",0);
        Temp2=getIntent().getIntExtra("温度2",0);
        Temp3=getIntent().getIntExtra("温度3",0);
        Temp4=getIntent().getIntExtra("温度4",0);
        Temp5=getIntent().getIntExtra("温度5",0);
        soilMoisture1=getIntent().getIntExtra("土壤湿度1",0);
        soilMoisture2=getIntent().getIntExtra("土壤湿度2",0);
        soilMoisture3=getIntent().getIntExtra("土壤湿度3",0);
        airHumidity1=getIntent().getIntExtra("空气湿度1",0);
        airHumidity2=getIntent().getIntExtra("空气湿度2",0);
        lightIntensity=getIntent().getIntExtra("光照强度",0);
        CO2=getIntent().getIntExtra("CO2浓度",0);
        conductivity=getIntent().getIntExtra("电导率",0);
        soilSalinity=getIntent().getIntExtra("盐分",0);

    }

    //实例化组件
    public void init(){
        tv_cn_farm_detail_name=findViewById(R.id.tv_cn_farm_detail_name);
        tv_en_farm_detail_name=findViewById(R.id.tv_en_farm_detail_name);
        icon_farm_detail=findViewById(R.id.icon_farm_detail);
        //传感器
        tv_temp1=findViewById(R.id.tv_temp1);
        tv_temp2=findViewById(R.id.tv_temp2);
        tv_temp3=findViewById(R.id.tv_temp3);
        tv_temp4=findViewById(R.id.tv_temp4);
        tv_temp5=findViewById(R.id.tv_temp5);
        tv_soilMoisture1=findViewById(R.id.tv_soilMoisture1);
        tv_soilMoisture2=findViewById(R.id.tv_soilMoisture2);
        tv_soilMoisture3=findViewById(R.id.tv_soilMoisture3);
        tv_airHumidity1=findViewById(R.id.tv_airHumidity1);
        tv_airHumidity2=findViewById(R.id.tv_airHumidity2);
        tv_lightIntensity=findViewById(R.id.tv_lightIntensity);
        tv_co2=findViewById(R.id.tv_co2);
        tv_conductivity=findViewById(R.id.tv_conductivity);
        //控制器（按钮）
        shebei1=findViewById(R.id.card_shebei1);
        shebei2=findViewById(R.id.card_shebei2);
        shebei3=findViewById(R.id.card_shebei3);
        shebei4=findViewById(R.id.card_shebei4);
        shebei5=findViewById(R.id.card_shebei5);
        tv_shebei1=findViewById(R.id.tv_shebei1);
        tv_shebei2=findViewById(R.id.tv_shebei2);
        tv_shebei3=findViewById(R.id.tv_shebei3);
        tv_shebei4=findViewById(R.id.tv_shebei4);
        tv_shebei5=findViewById(R.id.tv_shebei5);
        tv_shebei1_zhuangtai=findViewById(R.id.tv_shebei1_zhuangtai);
        tv_shebei2_zhuangtai=findViewById(R.id.tv_shebei2_zhuangtai);
        tv_shebei3_zhuangtai=findViewById(R.id.tv_shebei3_zhuangtai);
        tv_shebei4_zhuangtai=findViewById(R.id.tv_shebei4_zhuangtai);
        tv_shebei5_zhuangtai=findViewById(R.id.tv_shebei5_zhuangtai);
        icon_shebei1=findViewById(R.id.icon_shebei1);
        icon_shebei2=findViewById(R.id.icon_shebei2);
        icon_shebei3=findViewById(R.id.icon_shebei3);
        icon_shebei4=findViewById(R.id.icon_shebei4);
        icon_shebei5=findViewById(R.id.icon_shebei5);
        cl_shebei1=findViewById(R.id.cl_shebei1);
        cl_shebei2=findViewById(R.id.cl_shebei2);
        cl_shebei3=findViewById(R.id.cl_shebei3);
        cl_shebei4=findViewById(R.id.cl_shebei4);
        cl_shebei5=findViewById(R.id.cl_shebei5);
        sk_shebri4=findViewById(R.id.sk_shebei4);
        sk_shebri5=findViewById(R.id.sk_shebei5);
    }

    //初始化温度数据和农场名称
    public void assignment(){
        tv_cn_farm_detail_name.setText(farmCNName);
        tv_en_farm_detail_name.setText(farmENName);
        tv_temp1.setText(String.valueOf(Temp1));
        tv_temp2.setText(String.valueOf(Temp2));
        tv_temp3.setText(String.valueOf(Temp3));
        tv_temp4.setText(String.valueOf(Temp4));
        tv_temp5.setText(String.valueOf(Temp5));
        tv_soilMoisture1.setText(String.valueOf(soilMoisture1));
        tv_soilMoisture2.setText(String.valueOf(soilMoisture2));
        tv_soilMoisture3.setText(String.valueOf(soilMoisture3));
        tv_airHumidity1.setText(String.valueOf(airHumidity1));
        tv_airHumidity2.setText(String.valueOf(airHumidity2));
        tv_lightIntensity.setText(String.valueOf(lightIntensity));
        tv_co2.setText(String.valueOf(CO2));
        tv_conductivity.setText(String.valueOf(conductivity));
    }

    //控制组件点击后的事件
    public void onclicked(){
        shebei1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shebei1State==1){
                    shebei1State=shebei1State-1;
                }
                else if (shebei1State==0){
                    shebei1State=shebei1State+1;
                }

                switch (shebei1State){
                    case 0:
                        shebei1.setBackground(getResources().getDrawable(R.drawable.bg_equipment_notclicked));

                        tv_shebei1.setTextColor(getResources().getColor(R.color.CnEquipment));
                        tv_shebei1_zhuangtai.setText("关");
                        tv_shebei1_zhuangtai.setTextColor(getResources().getColor(R.color.bg_overcast));
                        icon_shebei1.setImageResource(R.drawable.icon_spray);
                        //在此发送关闭设备的代码

                        break;
                    case 1:
                        shebei1.setBackground(getResources().getDrawable(R.drawable.bg_equipment1_clicked));
                        tv_shebei1.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        tv_shebei1_zhuangtai.setText("开");
                        tv_shebei1_zhuangtai.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        icon_shebei1.setImageResource(R.drawable.icon_spray_onclicked);
                        //在此发送开启设备的代码

                        break;

                }
            }
        });
        shebei2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shebei2State==1){
                    shebei2State=shebei2State-1;
                }
                else if (shebei2State==0){
                    shebei2State=shebei2State+1;
                }

                switch (shebei2State){
                    case 0:
                        shebei2.setBackground(getResources().getDrawable(R.drawable.bg_equipment_notclicked));
                        tv_shebei2.setTextColor(getResources().getColor(R.color.CnEquipment));
                        tv_shebei2_zhuangtai.setText("关");
                        tv_shebei2_zhuangtai.setTextColor(getResources().getColor(R.color.bg_overcast));
                        icon_shebei2.setImageResource(R.drawable.icon_fan);
                        //在此发送关闭设备的代码

                        break;
                    case 1:
                        shebei2.setBackground(getResources().getDrawable(R.drawable.bg_equipment2_clicked));
                        tv_shebei2.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        tv_shebei2_zhuangtai.setText("开");
                        tv_shebei2_zhuangtai.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        icon_shebei2.setImageResource(R.drawable.icon_fan_onclicked);
                        //在此发送开启设备的代码

                        break;
                }
            }
        });
        shebei3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shebei3State==1){
                    shebei3State=shebei3State-1;
                }
                else if (shebei3State==0){
                    shebei3State=shebei3State+1;
                }

                switch (shebei3State){
                    case 0:
                        shebei3.setBackground(getResources().getDrawable(R.drawable.bg_equipment_notclicked));
                        tv_shebei3.setTextColor(getResources().getColor(R.color.CnEquipment));
                        tv_shebei3_zhuangtai.setText("关");
                        tv_shebei3_zhuangtai.setTextColor(getResources().getColor(R.color.bg_overcast));
                        icon_shebei3.setImageResource(R.drawable.icon_lighting);
                        //在此发送关闭设备的代码

                        break;
                    case 1:
                        shebei3.setBackground(getResources().getDrawable(R.drawable.bg_equipment3_clicked));
                        tv_shebei3.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        tv_shebei3_zhuangtai.setText("开");
                        tv_shebei3_zhuangtai.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        icon_shebei3.setImageResource(R.drawable.icon_lighting_onclicked);
                        //在此发送开启设备的代码

                        break;
                }
            }
        });



    }

    public void onseekbar(){
        sk_shebri4.setCustomSectionTextArray(new BubbleSeekBar.CustomSectionTextArray() {
            @NonNull
            @Override
            public SparseArray<String> onCustomize(int sectionCount, @NonNull SparseArray<String> array) {
                array.clear();
                array.put(0, "关闭");
                array.put(1, "停止");
                array.put(2, "开启");
                return array;
            }
        });
        sk_shebri4.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {

            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

                shebei4State = progress;

            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

                switch (shebei4State){

                    case 0:
                        shebei4.setBackground(getResources().getDrawable(R.drawable.bg_equipment_notclicked));
                        tv_shebei4.setTextColor(getResources().getColor(R.color.CnEquipment));
                        tv_shebei4_zhuangtai.setText("关");
                        tv_shebei4_zhuangtai.setTextColor(getResources().getColor(R.color.bg_overcast));
                        icon_shebei4.setImageResource(R.drawable.icon_rollfilm);
                        //在此发送关闭设备的代码

                        break;
                    case 1:
                        shebei4.setBackground(getResources().getDrawable(R.drawable.bg_equipment4_stoped));
                        tv_shebei4.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        tv_shebei4_zhuangtai.setText("停");
                        tv_shebei4_zhuangtai.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        icon_shebei4.setImageResource(R.drawable.icon_rollfilm_onclicked);
                        //在此发送开启设备的代码

                        break;
                    case 2:
                        shebei4.setBackground(getResources().getDrawable(R.drawable.bg_equipment4_clicked));
                        tv_shebei4.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        tv_shebei4_zhuangtai.setText("开");
                        tv_shebei4_zhuangtai.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        icon_shebei4.setImageResource(R.drawable.icon_rollfilm_onclicked);
                        //在此发送开启设备的代码

                        break;
                }

            }
        });

        sk_shebri5.setCustomSectionTextArray(new BubbleSeekBar.CustomSectionTextArray() {
            @NonNull
            @Override
            public SparseArray<String> onCustomize(int sectionCount, @NonNull SparseArray<String> array) {
                array.clear();
                array.put(0, "关闭");
                array.put(1, "停止");
                array.put(2, "开启");
                return array;
            }
        });
        sk_shebri5.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                shebei5State = progress;
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

                switch (shebei5State){

                    case 0:
                        shebei5.setBackground(getResources().getDrawable(R.drawable.bg_equipment_notclicked));
                        tv_shebei5.setTextColor(getResources().getColor(R.color.CnEquipment));
                        tv_shebei5_zhuangtai.setText("关");
                        tv_shebei5_zhuangtai.setTextColor(getResources().getColor(R.color.bg_overcast));
                        icon_shebei5.setImageResource(R.drawable.icon_sunshade);
                        //在此发送关闭设备的代码

                        break;
                    case 1:
                        shebei5.setBackground(getResources().getDrawable(R.drawable.bg_equipment5_stoped));
                        tv_shebei5.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        tv_shebei5_zhuangtai.setText("停");
                        tv_shebei5_zhuangtai.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        icon_shebei5.setImageResource(R.drawable.icon_sunshade_onclicked);
                        //在此发送开启设备的代码

                        break;
                    case 2:
                        shebei5.setBackground(getResources().getDrawable(R.drawable.bg_equipment5_clicked));
                        tv_shebei5.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        tv_shebei5_zhuangtai.setText("开");
                        tv_shebei5_zhuangtai.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        icon_shebei5.setImageResource(R.drawable.icon_sunshade_onclicked);
                        //在此发送开启设备的代码

                        break;
                }


            }
        });
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
