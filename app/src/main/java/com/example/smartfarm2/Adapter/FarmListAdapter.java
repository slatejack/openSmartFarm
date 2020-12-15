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

public class FarmListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list1;//农场名称
    private ArrayList<String> list2;//农场英文名
    private ArrayList<String> list3;//农场温度

    public FarmListAdapter(Context context,ArrayList<String> list1,ArrayList<String> list2,ArrayList<String> list3){
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

    //每绘制一次条目就会调用一次这个方法,在此方法中指定绘制条目的样式,
    //并把要显示的相应数据添加到对应的条目控件中
    //postion 表示下一次将要绘制第几个条目
    //contextView 默认值为null,我们给它重新赋值,用来指定条目样式
    //并获取条目中所有控件,给响应属性赋值
    @Override
    public View getView(int position, View contextView, ViewGroup parent) {

        String farmName=list1.get(position);
        String farmEnName=list2.get(position);
        String farmWenDu=list3.get(position);

        if (contextView == null) {
            // 通过LayoutInflater 类的 from 方法 再 使用 inflate()方法得到指定的布局
            // 得到ListView中要显示的条目的布局
            LayoutInflater from = LayoutInflater.from(context);
            contextView = from.inflate( R.layout.farm_card, null);//text涉及listview中的字体样式
            // 从要显示的条目布局中 获得指定的组件
            Temp.farmname = (TextView) contextView.findViewById(R.id.tv_farm_name);
            Temp.farmenname=(TextView)contextView.findViewById(R.id.tv_en_farm_name);
            Temp.farmzhuangtai=(TextView)contextView.findViewById(R.id.tv_farm_zhuangtai);
            Temp.icon_farmlist=(ImageView)contextView.findViewById(R.id.icon_farmlist);

            Temp.farmname.setText(farmName);
            Temp.farmenname.setText(farmEnName);
            if (farmWenDu=="温度过高"){
                Temp.farmzhuangtai.setText("温度过高");
                Temp.farmzhuangtai.setTextColor(contextView.getResources().getColor(R.color.err_highTemp));
                Temp.icon_farmlist.setImageResource(R.drawable.icon_nongchang_err);

            }
            else {
                Temp.farmzhuangtai.setText("正常");
                Temp.farmzhuangtai.setTextColor(contextView.getResources().getColor(R.color.bg_sun));
            }

        }

        return contextView;
    }
    //静态内部类,保证不一直查找此对象(优化)
    static class Temp {
        static TextView farmname;
        static TextView farmenname;
        static TextView farmzhuangtai;
        static ImageView icon_farmlist;
    }

}
