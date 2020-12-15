package com.example.smartfarm2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;

public class EquipmentDetails3Test extends AppCompatActivity {

    int state=0;//滑块当前状态
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
    BubbleSeekBar seekBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details3_test);
        init();
    }

    //初始化组件
    private void init(){
        icon_equipmentdetails=findViewById(R.id.icon_equipmentdetails3);
        tv_equipmentdetails_cnname=findViewById(R.id.tv_equipmentdetails3_cnname);
        tv_equipmentdetails_enname=findViewById(R.id.tv_equipmentdetails3_enname);
        tv_equipmentname=findViewById(R.id.tv_equipmentdetails_equipment1);
        tv_equipmenthome=findViewById(R.id.tv_equipmentdetails_equipment1_home);
        tv_equipmentstate=findViewById(R.id.tv_equipmentdetails_equipment1_zhuangtai);
        cd_equipmentdetails_equipment1=findViewById(R.id.cd_equipmentdetails_equipment1);
        icon_equipment=findViewById(R.id.icon_equipmentdetails_equipment1);
        seekBar1=findViewById(R.id.sk_equipment3_shebei1);
        getDataFromIntent();
        changecolor(getResources().getColor(R.color.bg_equipment));
        setOnclicked();
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
            case "xxx":
                icon_equipmentdetails.setImageResource(R.drawable.icon_waiting);
                icon_equipment.setImageResource(R.drawable.icon_waiting);
                seekBar1.setSecondTrackColor(getResources().getColor(R.color.sk_Equipment4));
                seekBar1.setCustomSectionTextArray(new BubbleSeekBar.CustomSectionTextArray() {
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
                break;
            case "遮阳设备":
                icon_equipmentdetails.setImageResource(R.drawable.icon_waiting);
                icon_equipment.setImageResource(R.drawable.icon_waiting);
                seekBar1.setSecondTrackColor(getResources().getColor(R.color.sk_Equipment5));
                seekBar1.setCustomSectionTextArray(new BubbleSeekBar.CustomSectionTextArray() {
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
        seekBar1.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                state=progress;
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {

                if (equipmentCnName.equals("xxxx")){
                    switch (state){
                        case 0:
                            cd_equipmentdetails_equipment1.setBackground(getResources().getDrawable(R.drawable.bg_equipment_notclicked));
                            tv_equipmentname.setTextColor(getResources().getColor(R.color.CnEquipment));
                            tv_equipmenthome.setTextColor(getResources().getColor(R.color.bg_overcast));
                            tv_equipmentstate.setText("关");
                            tv_equipmentstate.setTextColor(getResources().getColor(R.color.bg_overcast));
                            icon_equipment.setImageResource(R.drawable.icon_waiting);
                            //在此发送关闭设备的代码

                            break;
                        case 1:
                            cd_equipmentdetails_equipment1.setBackground(getResources().getDrawable(R.drawable.bg_equipment5_stoped));
                            tv_equipmentname.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                            tv_equipmenthome.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                            tv_equipmentstate.setText("停");
                            tv_equipmentstate.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                            icon_equipment.setImageResource(R.drawable.icon_waiting);
                            //在此发送停止设备的代码

                            break;
                        case 2:
                            cd_equipmentdetails_equipment1.setBackground(getResources().getDrawable(R.drawable.bg_equipment4_clicked));
                            tv_equipmentname.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                            tv_equipmenthome.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                            tv_equipmentstate.setText("开");
                            tv_equipmentstate.setTextColor(getResources().getColor(R.color.Equipmentclicked));
                            icon_equipment.setImageResource(R.drawable.icon_waiting);
                            //在此发送开启设备的代码

                            break;
                    }
                }


            }
        });
    }
}
