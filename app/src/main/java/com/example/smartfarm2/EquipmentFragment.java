package com.example.smartfarm2;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EquipmentFragment extends Fragment {

    public static final String TAG1 = EquipmentFragment.class.getSimpleName();

    ConstraintLayout bg_equipment;

    TextView tv_cn_equipment;
    TextView tv_en_equipment;

    CardView card_equipment1;
    CardView card_equipment2;
    CardView card_equipment3;
    CardView card_equipment4;
    CardView card_equipment5;

    ImageView icon_equipment1;
    ImageView icon_equipment2;
    ImageView icon_equipment3;
    ImageView icon_equipment4;
    ImageView icon_equipment5;

    TextView tv_cn_equipment1;
    TextView tv_cn_equipment2;
    TextView tv_cn_equipment3;
    TextView tv_cn_equipment4;
    TextView tv_cn_equipment5;

    TextView tv_en_equipment1;
    TextView tv_en_equipment2;
    TextView tv_en_equipment3;
    TextView tv_en_equipment4;
    TextView tv_en_equipment5;

    public EquipmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View txview=inflater.inflate(R.layout.fragment_equipment, container, false);
        init(txview);
        setResource();
        onclicked();
        return txview;
    }

    /**
     * 实例化部件部分
     * @param txview
     */
    public void init(View txview){

        //实例化标题部分
        tv_cn_equipment=txview.findViewById(R.id.tv_weathername);
        tv_en_equipment=txview.findViewById(R.id.tv_wenhou);
        bg_equipment=txview.findViewById(R.id.bg_weather);

        //实例化card部分
        card_equipment1=txview.findViewById(R.id.card_equipment1);
        card_equipment2=txview.findViewById(R.id.card_equipment2);
        card_equipment3=txview.findViewById(R.id.card_equipment3);
        card_equipment4=txview.findViewById(R.id.card_equipment4);
        card_equipment5=txview.findViewById(R.id.card_equipment5);

        //实例化图标部分
        icon_equipment1=txview.findViewById(R.id.icon_equipment1);
        icon_equipment2=txview.findViewById(R.id.icon_equipment2);
        icon_equipment3=txview.findViewById(R.id.icon_equipment3);
        icon_equipment4=txview.findViewById(R.id.icon_equipment4);
        icon_equipment5=txview.findViewById(R.id.icon_equipment5);

        //实例化中文文字部分
        tv_cn_equipment1=txview.findViewById(R.id.tv_cn_equipment1);
        tv_cn_equipment2=txview.findViewById(R.id.tv_cn_equipment2);
        tv_cn_equipment3=txview.findViewById(R.id.tv_cn_equipment3);
        tv_cn_equipment4=txview.findViewById(R.id.tv_cn_equipment4);
        tv_cn_equipment5=txview.findViewById(R.id.tv_cn_equipment5);

        //实例化英文文字部分
        tv_en_equipment1=txview.findViewById(R.id.tv_en_equipment1);
        tv_en_equipment2=txview.findViewById(R.id.tv_en_equipment2);
        tv_en_equipment3=txview.findViewById(R.id.tv_en_equipment3);
        tv_en_equipment4=txview.findViewById(R.id.tv_en_equipment4);
        tv_en_equipment5=txview.findViewById(R.id.tv_en_equipment5);
    }

    /**
     * 设置资源部分
     */
    public void setResource(){

        bg_equipment.setBackgroundColor(getResources().getColor(R.color.bg_equipment));
        changecolor(getResources().getColor(R.color.bg_equipment));
        tv_cn_equipment.setText("设备列表");
        tv_cn_equipment.setTextColor(getResources().getColor(R.color.weathername));
        tv_en_equipment.setText("Equipment list");
        tv_en_equipment.setTextColor(getResources().getColor(R.color.weathername));


        icon_equipment1.setImageResource(R.drawable.icon_waiting);
        icon_equipment2.setImageResource(R.drawable.icon_waiting);
        icon_equipment3.setImageResource(R.drawable.icon_waiting);
        icon_equipment4.setImageResource(R.drawable.icon_waiting);
        icon_equipment5.setImageResource(R.drawable.icon_waiting);

        tv_cn_equipment1.setText("xxxx");
        tv_cn_equipment1.setTextColor(getResources().getColor(R.color.CnEquipment));
        tv_en_equipment1.setText("xxxx");

        tv_cn_equipment2.setText("xxxx");
        tv_cn_equipment2.setTextColor(getResources().getColor(R.color.CnEquipment));
        tv_en_equipment2.setText("xxxx");

        tv_cn_equipment3.setText("xxxx");
        tv_cn_equipment3.setTextColor(getResources().getColor(R.color.CnEquipment));
        tv_en_equipment3.setText("xxxx");

        tv_cn_equipment4.setText("xxxx");
        tv_cn_equipment4.setTextColor(getResources().getColor(R.color.CnEquipment));
        tv_en_equipment4.setText("xxxx");

        tv_cn_equipment5.setText("xxxx");
        tv_cn_equipment5.setTextColor(getResources().getColor(R.color.CnEquipment));
        tv_en_equipment5.setText("xxxx");

    }

    /**
     * 点击事件类
     */
    public void onclicked(){

        card_equipment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),EquipmentDetailsTest.class);
                intent.putExtra("设备名称","xxxx");
                intent.putExtra("EquipmentName","xxxx");
                intent.putExtra("设备归属","xxxx");
                intent.putExtra("设备状态","正在获取");
                startActivity(intent);
            }
        });

        card_equipment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //同上，可根据自己的需求进行添加
            }
        });

        card_equipment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //同上，可根据自己的需求进行添加
            }
        });

        card_equipment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //同上，可根据自己的需求进行添加
            }
        });

        card_equipment5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //同上，可根据自己的需求进行添加
            }
        });
    }

    public static EquipmentFragment newInstance() {
        return new EquipmentFragment();
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
        if (Build.VERSION.SDK_INT >= Build
                .VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(
                    TransitionInflater.from(getContext())
                            .inflateTransition(android.R.transition.move));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ConstraintLayout bg_weather = (ConstraintLayout)getView().findViewById(R.id.bg_weather);
        final TextView tv_weathername=(TextView)getView().findViewById(R.id.tv_weathername);
        final TextView tv_wenhou=(TextView)getView().findViewById(R.id.tv_wenhou);

        getActivity().findViewById(R.id.navigation_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment farmListFragment=getFragmentManager().findFragmentByTag(TAG1);

                if (farmListFragment==null)
                    farmListFragment=FarmListFragment.newInstance();

                    Bundle bundle=new Bundle();
                    bundle.putInt( "点击次数",1 );//点击次数为多次
                    farmListFragment.setArguments(bundle);

                    getFragmentManager()
                        .beginTransaction()
                        .addSharedElement(bg_weather, ViewCompat.getTransitionName(bg_weather))
                        .addSharedElement(tv_weathername,ViewCompat.getTransitionName(tv_weathername))
                        .addSharedElement(tv_wenhou,ViewCompat.getTransitionName(tv_wenhou))
                        .addToBackStack(TAG1)
                        .replace(R.id.ViewFragment, farmListFragment)
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
