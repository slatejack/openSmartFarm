package com.example.smartfarm2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class EquipmentDetailsTest extends AppCompatActivity {

    int state=0;//按钮当前状态
    String equipmentCnName;
    String equipmentEnName;
    String equipmentHome;
    String equipmentState;

    ImageView icon_equipmentdetails;
    TextView tv_equipmentdetails_cnname;
    TextView tv_equipmentdetails_enname;

    TextView tv_equipmentname;
    TextView tv_equipmenthome;
    TextView tv_equipmentstate;
    ImageView icon_equipment;
    CardView cd_equipmentdetails_equipment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details_test);
        changecolor(getResources().getColor(R.color.bg_equipment));
        init();
        setOnclicked();

    }

    //初始化控件
    private void init(){
        tv_equipmentdetails_cnname=findViewById(R.id.tv_equipmentdetails_cnname);
        tv_equipmentdetails_enname=findViewById(R.id.tv_equipmentdetails_enname);
        icon_equipmentdetails=findViewById(R.id.icon_equipmentdetails);
        icon_equipment=findViewById(R.id.icon_equipmentdetails_equipment1);
        tv_equipmentname=findViewById(R.id.tv_equipmentdetails_equipment1);
        tv_equipmenthome=findViewById(R.id.tv_equipmentdetails_equipment1_home);
        tv_equipmentstate=findViewById(R.id.tv_equipmentdetails_equipment1_zhuangtai);
        cd_equipmentdetails_equipment1=findViewById(R.id.cd_equipmentdetails_equipment1);
        getDataFromIntent();
    }
    //接收Intent传递的数据
    private void getDataFromIntent(){
        equipmentCnName=getIntent().getStringExtra("设备名称");
        equipmentEnName=getIntent().getStringExtra("EquipmentName");
        equipmentHome=getIntent().getStringExtra("设备归属");
        equipmentState=getIntent().getStringExtra("设备状态");
        setView();
    }
    //将值赋予界面组件
    private void setView(){
        tv_equipmentdetails_cnname.setText(equipmentCnName);
        tv_equipmentdetails_enname.setText(equipmentEnName);
        tv_equipmentname.setText(equipmentCnName);
        tv_equipmenthome.setText(equipmentHome);
        tv_equipmentstate.setText(equipmentState);
        switch (equipmentCnName){
            case "xxxx":
                icon_equipmentdetails.setImageResource(R.drawable.icon_waiting);
                icon_equipment.setImageResource(R.drawable.icon_waiting);
                break;
        }
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
    private void setOnclicked(){
        cd_equipmentdetails_equipment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state==0){
                    state=state+1;
                    switch (equipmentCnName){
                        case "xxxx":
                            cd_equipmentdetails_equipment1.setBackground(getResources().getDrawable(R.drawable.bg_equipment1_clicked));
                            icon_equipment.setImageResource(R.drawable.icon_waiting);
                            tv_equipmentname.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                            tv_equipmenthome.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                            tv_equipmentstate.setText("开");
                            tv_equipmentstate.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                            break;
                    }
                }
                else if (state==1){
                    state=state-1;
                    switch (equipmentCnName){
                        case "xxxx":
                            cd_equipmentdetails_equipment1.setBackground(getResources().getDrawable(R.drawable.bg_equipment_notclicked));
                            icon_equipment.setImageResource(R.drawable.icon_waiting);
                            tv_equipmentname.setTextColor(getResources().getColor(R.color.CnEquipment));
                            tv_equipmenthome.setTextColor(getResources().getColor(R.color.bg_overcast));
                            tv_equipmentstate.setText("关");
                            tv_equipmentstate.setTextColor(getResources().getColor(R.color.bg_overcast));
                            break;
                    }
                }
            }
        });
    }
}
