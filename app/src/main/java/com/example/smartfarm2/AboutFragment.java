package com.example.smartfarm2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.kongzue.dialog.v3.CustomDialog;

import static com.example.smartfarm2.EquipmentFragment.TAG1;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    TextView AppName;
    TextView Banben;
    TextView Aruthor;
    Button logout;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View txview=inflater.inflate(R.layout.fragment_about, container, false);
        AppName=txview.findViewById(R.id.tv_appname);
        Banben=txview.findViewById(R.id.tv_buildnum);
        Aruthor=txview.findViewById(R.id.tv_zhuzuoquan);
        logout=txview.findViewById(R.id.bt_logout);

        AppName.setText("智慧农场");
        Banben.setText("版本 V2.0.0");
        changecolor(Color.WHITE);
        setonclicked();
        return txview;
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

    private void setonclicked(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用未实例化的自定义布局制作dialog
                CustomDialog.show((AppCompatActivity) getActivity(), R.layout.dialog_info, new CustomDialog.OnBindView() {
                    @Override
                    public void onBind(final CustomDialog dialog, View v) {
                        TextView dialog_title=v.findViewById(R.id.title);
                        TextView dialog_btnOk = v.findViewById(R.id.positiveTextView);
                        TextView dialog_btncancle=v.findViewById(R.id.negativeTextView);
                        TextView dialog_message=v.findViewById(R.id.message);

                        dialog_title.setText("确认注销吗？");
                        dialog_message.setText("注销后，下次需要重新登录");

                        dialog_btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SharedPreferences share =getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
                                share.edit().putBoolean("LoginBool", false).commit();
                                Intent intent = new Intent(getContext(), loginActivity.class);
                                startActivity(intent);
                                dialog.doDismiss();
                            }
                        });
                        dialog_btncancle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.doDismiss();
                            }
                        });
                    }
                });

            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ConstraintLayout bg_weather = (ConstraintLayout)getView().findViewById(R.id.bg_weather);
        final TextView tv_weathername=(TextView)getView().findViewById(R.id.tv_weathername);
        final TextView tv_wenhou=(TextView)getView().findViewById(R.id.tv_wenhou);


        getActivity().findViewById(R.id.navigation_dashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment equipmentFragment=getFragmentManager().findFragmentByTag(TAG1);

                if (equipmentFragment==null)
                    equipmentFragment=EquipmentFragment.newInstance();

                        getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ViewFragment, equipmentFragment)
                        .commit();

            }
        });
    }
}
