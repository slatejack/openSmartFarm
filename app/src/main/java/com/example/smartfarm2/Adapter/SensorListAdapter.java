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

public class SensorListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list1;//传感器名称
    private ArrayList<String> list2;//传感器数值

    public SensorListAdapter(Context context,ArrayList<String> list1,ArrayList<String> list2){
        super();
        this.context=context;
        this.list1=list1;
        this.list2=list2;
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

        String sensorName=list1.get(position);
        String sensorValue=list2.get(position);

        if (contextView == null) {
            // 通过LayoutInflater 类的 from 方法 再 使用 inflate()方法得到指定的布局
            // 得到ListView中要显示的条目的布局
            LayoutInflater from = LayoutInflater.from(context);
            contextView = from.inflate( R.layout.farm_details_card, null);//text涉及listview中的字体样式
            // 从要显示的条目布局中 获得指定的组件
            Temp.sensorname = (TextView) contextView.findViewById(R.id.tv_farm_details_card_sensor_name);
            Temp.sensorvalue=(TextView)contextView.findViewById(R.id.tv_farm_details_card_sensor_value);
            Temp.icon_sensorlist=(ImageView)contextView.findViewById(R.id.icon_farm_details_card);

            Temp.sensorname.setText(sensorName);
            Temp.sensorvalue.setText(sensorValue);
            switch (sensorName){
                case "空气温湿度":
                    Temp.icon_sensorlist.setImageResource(R.drawable.icon_temandhum);
                    break;
                case "土壤温湿度":
                    Temp.icon_sensorlist.setImageResource(R.drawable.icon_temandhum);
                    break;
                case "土壤酸碱度":
                    Temp.icon_sensorlist.setImageResource(R.drawable.icon_soilph);
                    break;
            }
        }
        return contextView;
    }
    //静态内部类,保证不一直查找此对象(优化)
    static class Temp {
        static TextView sensorname;
        static TextView sensorvalue;
        static ImageView icon_sensorlist;
    }
}
