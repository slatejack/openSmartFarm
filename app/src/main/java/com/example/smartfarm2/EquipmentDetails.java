package com.example.smartfarm2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartfarm2.Adapter.EquipmentListAdapter;

import java.util.ArrayList;

public class EquipmentDetails extends AppCompatActivity {

    private static final String TAG = "设备列表";
    String equipmentCnName;
    String equipmentEnName;

    private ArrayList<String> list1=new ArrayList<String>();//设备名称
    private ArrayList<String> list2=new ArrayList<String>();//设备归属地
    private ArrayList<String> list3=new ArrayList<String>();//设备状态
    EquipmentListAdapter equipmentListAdapter;


    ListView lv_equipment;
    ImageView icon_equipmentdetails;
    TextView tv_equipmentdetails_cnname;
    TextView tv_equipmentdetails_enname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details);
        changecolor(getResources().getColor(R.color.bg_equipment));
        init();

    }

    //初始化控件
    private void init(){
        icon_equipmentdetails=findViewById(R.id.icon_equipmentdetails);
        tv_equipmentdetails_cnname=findViewById(R.id.tv_equipmentdetails_cnname);
        tv_equipmentdetails_enname=findViewById(R.id.tv_equipmentdetails_enname);
        lv_equipment=findViewById(R.id.lv_equipment);
        getDataFromIntent();
    }
    //获取前一界面发出的数据
    private void getDataFromIntent(){
        equipmentCnName=getIntent().getStringExtra("设备名称");
        equipmentEnName=getIntent().getStringExtra("EquipmentName");

        list1.add(getIntent().getStringExtra("设备名称"));
        list2.add(getIntent().getStringExtra("设备归属"));
        list3.add(getIntent().getStringExtra("设备状态"));
        initIcon();
        initListView();
    }
    //初始化界面图标文字
    private void initIcon(){
        Log.i(TAG, "设备名称："+equipmentCnName);
        tv_equipmentdetails_cnname.setText(equipmentCnName);
        tv_equipmentdetails_enname.setText(equipmentEnName);
        switch (equipmentCnName){
            case "喷淋设备":
                icon_equipmentdetails.setImageResource(R.drawable.icon_spray);
                break;
            case "环流风机":
                icon_equipmentdetails.setImageResource(R.drawable.icon_fan);
                break;
            case "照明设备":
                icon_equipmentdetails.setImageResource(R.drawable.icon_lighting);
                break;
        }
    }
    //初始化listview
    private void initListView(){
        equipmentListAdapter=new EquipmentListAdapter(getApplicationContext(),list1,list2,list3);
        lv_equipment.setAdapter(equipmentListAdapter);
        lv_equipment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
