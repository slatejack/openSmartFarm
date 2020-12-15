package com.example.smartfarm2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartfarm2.R;

import java.util.ArrayList;

public class EquipmentListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list1;//设备名称
    private ArrayList<String> list2;//设备所属地
    private ArrayList<String> list3;//设备状态

    public EquipmentListAdapter(Context context,ArrayList<String> list1,ArrayList<String> list2,ArrayList<String> list3){
        super();
        this.context=context;
        this.list1=list1;
        this.list2=list2;
        this.list3=list3;
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int position) {
        return list1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View contextView, ViewGroup parent) {
        String equipmentName=list1.get(position);
        String equipmentHome=list2.get(position);
        String equipmentState=list3.get(position);

        if (contextView == null) {
            // 通过LayoutInflater 类的 from 方法 再 使用 inflate()方法得到指定的布局
            // 得到ListView中要显示的条目的布局
            LayoutInflater from = LayoutInflater.from(context);
            contextView = from.inflate( R.layout.equipment_card, null);//text涉及listview中的字体样式

            // 从要显示的条目布局中 获得指定的组件
            Temp.equipmentname=(TextView)contextView.findViewById(R.id.tv_equipmentdetails_equipment1);
            Temp.equipmenthome=(TextView)contextView.findViewById(R.id.tv_equipmentdetails_equipment1_home);
            Temp.equipmentstate=(TextView)contextView.findViewById(R.id.tv_equipmentdetails_equipment1_zhuangtai);
            Temp.icon_equipment=(ImageView)contextView.findViewById(R.id.icon_equipmentdetails_equipment1);

            Temp.equipmentname.setText(equipmentName);
            Temp.equipmenthome.setText(equipmentHome);
            Temp.equipmentstate.setText(equipmentState);

            switch (equipmentName){
                case "喷淋设备":
                    Temp.icon_equipment.setImageResource(R.drawable.icon_spray);
                    break;
                case "环流风机":
                    Temp.icon_equipment.setImageResource(R.drawable.icon_fan);
                    break;
                case "照明设备":
                    Temp.icon_equipment.setImageResource(R.drawable.icon_lighting);
                    break;
            }
        }


        return contextView;
    }

    //静态内部类,保证不一直查找此对象(优化)
    static class Temp {
        static TextView equipmentname;
        static TextView equipmenthome;
        static TextView equipmentstate;
        static ImageView icon_equipment;
    }


}
