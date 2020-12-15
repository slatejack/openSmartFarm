package com.example.smartfarm2;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int clickchance=0;
    private TextView mTextMessage;
    private FarmListFragment farmListFragment;
    private EquipmentFragment equipmentFragment;
    private AboutFragment aboutFragment;

    private ConstraintLayout bg_weather;
    private TextView tv_weathername;
    private TextView tv_wenhou;
    private ImageView icon_weather;

    public static final String TAG1 = EquipmentFragment.class.getSimpleName();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            //FragmentTransaction transaction = getFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                        clickchance=clickchance+1;
                        farmListFragment=new FarmListFragment();
                        Bundle bundle=new Bundle();
                        bundle.putInt( "点击次数",clickchance );//点击次数
                        farmListFragment.setArguments(bundle);
                        getSupportFragmentManager()
                                .beginTransaction()
                                //第一次创建界面时添加到队列中
                                //.add(R.id.ViewFragment,farmListFragment)
                                .replace(R.id.ViewFragment,farmListFragment)
                                .commit();
                    return true;

                case R.id.navigation_dashboard:

                        equipmentFragment=new EquipmentFragment();
                    if (equipmentFragment==null)
                        equipmentFragment=EquipmentFragment.newInstance();

                        equipmentFragment.setArguments(getIntent().getExtras());
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.ViewFragment,equipmentFragment)
                                .commit();


                    return true;

                case R.id.navigation_notifications:
                    aboutFragment=new AboutFragment();
                    aboutFragment.setArguments(getIntent().getExtras());
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.ViewFragment,aboutFragment)
                            .commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        init();

        farmListFragment=new FarmListFragment();
        Bundle bundle=new Bundle();
        bundle.putInt( "点击次数",clickchance );//点击次数
        farmListFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                //第一次创建界面时添加到队列中
                //.add(R.id.ViewFragment,farmListFragment)
                .replace(R.id.ViewFragment,farmListFragment)
                .commit();
    }

    private void init(){
        bg_weather=findViewById(R.id.bg_weather);
        tv_weathername=findViewById(R.id.tv_weathername);
        tv_wenhou=findViewById(R.id.tv_wenhou);
        icon_weather=findViewById(R.id.icon_weather);
    }



}
