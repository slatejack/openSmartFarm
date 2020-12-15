package com.example.smartfarm2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;

public class FarmDetailsNew extends AppCompatActivity {

    String farmCNName;//农场中文名
    String farmENName;//农场英文名
    String farmdetails;//农场状态


    double lightIntensity=0;//光照强度
    double PM25=0;//pm2.5浓度
    double windspeed=0;//风速
    String winddirection="";//风向
    double rainfall=0;//雨量

    //设备按钮当前状态
    int shebei1State=0;
    int shebei2State=0;
    int shebei3State=0;
    int shebei4State=0;
    int shebei5State=0;
    int shebei6State=0;

    TextView tv_cn_farm_detail_name;//农场中文名
    TextView tv_en_farm_detail_name;//农场英文名
    //传感器状态
    TextView tv_lightIntensity;//光照强度
    TextView tv_Pm25;//PM2.5强度
    TextView tv_windspeed;//风速
    TextView tv_winddirection;//风向
    TextView tv_rainfall;//雨量
    TextView tv_airtemandhum;//空气温湿度
    TextView tv_soiltemandhum;//土壤温湿度
    TextView tv_soilph;//土壤酸碱度
    TextView tv_sensor1;
    TextView tv_sensor2;
    TextView tv_sensor3;
    TextView tv_sensor4;
    TextView tv_sensor5;
    TextView tv_sensor6;
    TextView tv_sensor7;
    TextView tv_sensor8;
    CardView cd_sensor1;
    CardView cd_sensor2;
    CardView cd_sensor3;
    CardView cd_sensor4;
    CardView cd_sensor5;
    CardView cd_sensor6;
    CardView cd_sensor7;
    CardView cd_sensor8;

    //控制器开关
    CardView shebei1;
    CardView shebei2;
    CardView shebei3;
    CardView shebei4;
    CardView shebei5;
    CardView shebei6;
    TextView tv_shebei1;
    TextView tv_shebei2;
    TextView tv_shebei3;
    TextView tv_shebei4;
    TextView tv_shebei5;
    TextView tv_shebei6;
    TextView tv_shebei1_zhuangtai;
    TextView tv_shebei2_zhuangtai;
    TextView tv_shebei3_zhuangtai;
    TextView tv_shebei4_zhuangtai;
    TextView tv_shebei5_zhuangtai;
    TextView tv_shebei6_zhuangtai;
    ImageView icon_shebei1;
    ImageView icon_shebei2;
    ImageView icon_shebei3;
    ImageView icon_shebei4;
    ImageView icon_shebei5;
    ImageView  icon_shebei6;
    ConstraintLayout cl_shebei1;
    ConstraintLayout cl_shebei2;
    ConstraintLayout cl_shebei3;
    ConstraintLayout cl_shebei4;
    ConstraintLayout cl_shebei5;
    ConstraintLayout cl_shebei6;
    BubbleSeekBar sk_shebri4;
    BubbleSeekBar sk_shebri5;

    ImageView icon_farm_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_details_new);

        changecolor(Color.WHITE);
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

        lightIntensity=getIntent().getDoubleExtra("光照强度",0.0);
        PM25=getIntent().getDoubleExtra("pm2.5",0.0);
        windspeed=getIntent().getDoubleExtra("风速",0.0);
        winddirection=getIntent().getStringExtra("风向");
        rainfall=getIntent().getDoubleExtra("雨量",0.0);
    }

    //实例化组件
    public void init(){
        tv_cn_farm_detail_name=findViewById(R.id.tv_cn_farm_detail_name);
        tv_en_farm_detail_name=findViewById(R.id.tv_en_farm_detail_name);
        icon_farm_detail=findViewById(R.id.icon_farm_detail);

        //传感器
        tv_airtemandhum=findViewById(R.id.tv_monitor1value);
        tv_lightIntensity=findViewById(R.id.tv_monitor2value);
        tv_Pm25=findViewById(R.id.tv_monitor3value);
        tv_soiltemandhum=findViewById(R.id.tv_monitor4value);
        tv_soilph=findViewById(R.id.tv_monitor5value);
        tv_windspeed=findViewById(R.id.tv_monitor6value);
        tv_winddirection=findViewById(R.id.tv_monitor7value);
        tv_rainfall=findViewById(R.id.tv_monitor8value);
        tv_sensor1=findViewById(R.id.tv_monitor1);
        tv_sensor2=findViewById(R.id.tv_monitor2);
        tv_sensor3=findViewById(R.id.tv_monitor3);
        tv_sensor4=findViewById(R.id.tv_monitor4);
        tv_sensor5=findViewById(R.id.tv_monitor5);
        tv_sensor6=findViewById(R.id.tv_monitor6);
        tv_sensor7=findViewById(R.id.tv_monitor7);
        tv_sensor8=findViewById(R.id.tv_monitor8);
        cd_sensor1=findViewById(R.id.cd_zhuangtai1);
        cd_sensor2=findViewById(R.id.cd_zhuangtai2);
        cd_sensor3=findViewById(R.id.cd_zhuangtai3);
        cd_sensor4=findViewById(R.id.cd_zhuangtai4);
        cd_sensor5=findViewById(R.id.cd_zhuangtai5);
        cd_sensor6=findViewById(R.id.cd_zhuangtai6);
        cd_sensor7=findViewById(R.id.cd_zhuangtai7);
        cd_sensor8=findViewById(R.id.cd_zhuangtai8);

        //控制器（按钮）
        shebei1=findViewById(R.id.card_shebei1);
        shebei2=findViewById(R.id.card_shebei2);
        shebei3=findViewById(R.id.card_shebei3);
        shebei4=findViewById(R.id.card_shebei4);
        shebei5=findViewById(R.id.card_shebei5);
        shebei6=findViewById(R.id.card_shebei6);
        tv_shebei1=findViewById(R.id.tv_shebei1);
        tv_shebei2=findViewById(R.id.tv_shebei2);
        tv_shebei3=findViewById(R.id.tv_shebei3);
        tv_shebei4=findViewById(R.id.tv_shebei4);
        tv_shebei5=findViewById(R.id.tv_shebei5);
        tv_shebei6=findViewById(R.id.tv_shebei6);
        tv_shebei1_zhuangtai=findViewById(R.id.tv_shebei1_zhuangtai);
        tv_shebei2_zhuangtai=findViewById(R.id.tv_shebei2_zhuangtai);
        tv_shebei3_zhuangtai=findViewById(R.id.tv_shebei3_zhuangtai);
        tv_shebei4_zhuangtai=findViewById(R.id.tv_shebei4_zhuangtai);
        tv_shebei5_zhuangtai=findViewById(R.id.tv_shebei5_zhuangtai);
        tv_shebei6_zhuangtai=findViewById(R.id.tv_shebei6_zhuangtai);
        icon_shebei1=findViewById(R.id.icon_shebei1);
        icon_shebei2=findViewById(R.id.icon_shebei2);
        icon_shebei3=findViewById(R.id.icon_shebei3);
        icon_shebei4=findViewById(R.id.icon_shebei4);
        icon_shebei5=findViewById(R.id.icon_shebei5);
        icon_shebei6=findViewById(R.id.icon_shebei6);
        cl_shebei1=findViewById(R.id.cl_shebei1);
        cl_shebei2=findViewById(R.id.cl_shebei2);
        cl_shebei3=findViewById(R.id.cl_shebei3);
        cl_shebei4=findViewById(R.id.cl_shebei4);
        cl_shebei5=findViewById(R.id.cl_shebei5);
        cl_shebei6=findViewById(R.id.cl_shebei6);
        sk_shebri4=findViewById(R.id.sk_shebei4);
        sk_shebri5=findViewById(R.id.sk_shebei5);
    }

    //初始化温度数据和农场名称
    public void assignment(){
        tv_cn_farm_detail_name.setText(farmCNName);
        tv_en_farm_detail_name.setText(farmENName);
        /*
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
        */
        tv_airtemandhum.setText("点击查看");
        tv_lightIntensity.setText(String.valueOf(lightIntensity));
        tv_Pm25.setText(String.valueOf(PM25));
        tv_soiltemandhum.setText("点击查看");
        tv_soilph.setText("点击查看");
        tv_windspeed.setText(String.valueOf(windspeed));
        tv_winddirection.setText(String.valueOf(winddirection));
        tv_rainfall.setText(String.valueOf(rainfall));
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
        shebei6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shebei6State==1){
                    shebei6State=shebei6State-1;
                }
                else if (shebei6State==0){
                    shebei6State=shebei6State+1;
                }

                switch (shebei6State){
                    case 0:
                        shebei6.setBackground(getResources().getDrawable(R.drawable.bg_equipment_notclicked));
                        tv_shebei6.setTextColor(getResources().getColor(R.color.CnEquipment));
                        tv_shebei6_zhuangtai.setText("关");
                        tv_shebei6_zhuangtai.setTextColor(getResources().getColor(R.color.bg_overcast));
                        icon_shebei6.setImageResource(R.drawable.icon_birdscarer);
                        //在此发送关闭设备的代码

                        break;
                    case 1:
                        shebei6.setBackground(getResources().getDrawable(R.drawable.bg_equipment6_clicked));
                        tv_shebei6.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        tv_shebei6_zhuangtai.setText("开");
                        tv_shebei6_zhuangtai.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                        icon_shebei6.setImageResource(R.drawable.icon_birdscarer_onclicked);
                        //在此发送开启设备的代码

                        break;
                }
            }
        });
        cd_sensor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),FarmDetailsSensorDetails.class);
                intent.putExtra("sensorName",tv_sensor1.getText().toString());
                startActivity(intent);
            }
        });
        cd_sensor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),FarmDetailsSensorDetails.class);
                intent.putExtra("sensorName",tv_sensor4.getText().toString());
                startActivity(intent);
            }
        });
        cd_sensor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),FarmDetailsSensorDetails.class);
                intent.putExtra("sensorName",tv_sensor5.getText().toString());
                startActivity(intent);
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
