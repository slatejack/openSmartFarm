package com.example.smartfarm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartfarm2.DataBase.DBOpenHelper;
import com.example.smartfarm2.DataBase.User;
import com.example.smartfarm2.DataBase.Weather;
import com.kongzue.dialog.v3.CustomDialog;
import com.kongzue.dialog.v3.MessageDialog;

import java.util.ArrayList;

public class loginActivity extends AppCompatActivity {

    private static final String TAG = "login";
    private DBOpenHelper mDBOpenHelper;//用户数据库操作类
    private ArrayList<User> data;//用户数据库数据

    String username="";
    String userpass="";
    int role=0;

    EditText tv_username;
    EditText tv_userpass;
    Button bt_login;
    TextView tv_forgetpassworld;
    TextView tv_registeruser;
    ImageButton imgbt_clearusername;
    ImageButton imgbt_clearuserpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        changecolor(Color.WHITE);
        initDatabase();
        getSharedData();
        init();
        setOnclicked();
    }

    //实例化控件
    private void init(){
        tv_username=findViewById(R.id.et_username);
        tv_userpass=findViewById(R.id.et_userpass);
        bt_login=findViewById(R.id.bt_login);
        tv_forgetpassworld=findViewById(R.id.tv_forgetpassworld);
        tv_registeruser=findViewById(R.id.tv_registeruser);
        imgbt_clearusername=findViewById(R.id.imgbt_clearusername);
        imgbt_clearuserpass=findViewById(R.id.imgbt_clearuserpass);
    }

    //点击事件
    private void setOnclicked(){
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_username.getText().toString() == data.get(0).getUsername()&&tv_userpass.getText().toString() == data.get(0).getUserpass()){

                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    username=data.get(0).getUsername();
                    userpass=data.get(0).getUserpass();
                    role=data.get(0).getRole();
                    intent.putExtra("Username",username);
                    intent.putExtra("Role",role);
                    // 创建SharedPreferences对象用于储存帐号和密码,并将其私有化
                    SharedPreferences share = getSharedPreferences("Login", Context.MODE_PRIVATE);
                    // 获取编辑器来存储数据到sharedpreferences中
                    SharedPreferences.Editor editor = share.edit();
                    editor.putString("Username", username);
                    editor.putString("Password", userpass);
                    editor.putBoolean("LoginBool", true);
                    editor.putInt("Role", role);
                    // 将数据提交到sharedpreferences中
                    editor.commit();
                    startActivity(intent);
                }

                else if (tv_username.getText().toString().equals(data.get(1).getUsername())&&tv_userpass.getText().toString().equals(data.get(1).getUserpass())){

                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    username=data.get(1).getUsername();
                    userpass=data.get(1).getUserpass();
                    role=data.get(1).getRole();
                    intent.putExtra("Username",username);
                    intent.putExtra("Role",role);
                    // 创建SharedPreferences对象用于储存帐号和密码,并将其私有化
                    SharedPreferences share = getSharedPreferences("Login", Context.MODE_PRIVATE);
                    // 获取编辑器来存储数据到sharedpreferences中
                    SharedPreferences.Editor editor = share.edit();
                    editor.putString("Username", username);
                    editor.putString("Password", userpass);
                    editor.putBoolean("LoginBool", true);
                    editor.putInt("Role", role);
                    // 将数据提交到sharedpreferences中
                    editor.commit();
                    startActivity(intent);
                }

                else if (tv_username.getText().toString().equals(data.get(1).getUsername())==false||tv_username.getText().toString() == data.get(0).getUsername()==false){
                    Toast.makeText(getApplicationContext(),"用户名错误，请检查用户名",Toast.LENGTH_SHORT).show();
                }

                else if (tv_userpass.getText().toString().equals(data.get(1).getUserpass())==false||tv_userpass.getText().toString().equals(data.get(0).getUserpass())==false){
                    Toast.makeText(getApplicationContext(),"密码错误，请检查密码",Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.i(TAG, "未知登录");
                    Log.i(TAG, "输入用户:"+tv_username.getText().toString());
                    Log.i(TAG, "数据库用户:"+data.get(0).getUsername());
                    Log.i(TAG, "数据库用户:"+data.get(1).getUsername());
                    Log.i(TAG, "输入密码:"+tv_userpass.getText().toString());
                    Log.i(TAG, "数据库密码:"+data.get(0).getUserpass());
                    Log.i(TAG, "数据库密码:"+data.get(1).getUserpass());
                }
            }
        });

        imgbt_clearusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_username.setText("");
            }
        });
        imgbt_clearuserpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_userpass.setText("");
            }
        });
        tv_registeruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MessageDialog.show(loginActivity.this, "用户注册", "本软件暂不开放注册，如需调试功能请联系QQ:1815846446，以获取用户名密码", "确定");
                //使用未实例化的自定义布局制作dialog
                CustomDialog.show(loginActivity.this, R.layout.dialog_info, new CustomDialog.OnBindView() {
                    @Override
                    public void onBind(final CustomDialog dialog, View v) {
                            TextView dialog_title=v.findViewById(R.id.title);
                            TextView dialog_btnOk = v.findViewById(R.id.positiveTextView);
                            TextView dialog_btncancle=v.findViewById(R.id.negativeTextView);
                            TextView dialog_message=v.findViewById(R.id.message);

                            dialog_title.setText("用户注册");
                            dialog_message.setText("本软件暂不开放注册，如需调试功能请联系QQ:1815846446，以获取用户名密码");
                            dialog_btncancle.setVisibility(View.GONE);
                            dialog_btnOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.doDismiss();
                            }
                        });
                    }
                });
            }
        });
        tv_forgetpassworld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MessageDialog.show(loginActivity.this, "忘记密码", "请联系QQ:1815846446以获取用户名密码", "确定");
                //使用未实例化的自定义布局制作dialog
                CustomDialog.show(loginActivity.this, R.layout.dialog_info, new CustomDialog.OnBindView() {
                    @Override
                    public void onBind(final CustomDialog dialog, View v) {
                        TextView dialog_title=v.findViewById(R.id.title);
                        TextView dialog_btnOk = v.findViewById(R.id.positiveTextView);
                        TextView dialog_btncancle=v.findViewById(R.id.negativeTextView);
                        TextView dialog_message=v.findViewById(R.id.message);

                        dialog_title.setText("忘记密码");
                        dialog_message.setText("请联系QQ:1815846446，以获取用户名密码");
                        dialog_btncancle.setVisibility(View.GONE);
                        dialog_btnOk.setOnClickListener(new View.OnClickListener() {
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

    // 获取sharedpreferences对象
    private void getSharedData(){
        SharedPreferences share = getSharedPreferences("Login", Context.MODE_PRIVATE);
        username = share.getString("Username", "");
        userpass = share.getString("Password", "");
        role=share.getInt("Role",0);
        if (share != null) {
            if (share.getBoolean("LoginBool", false)) {
                // 跳转到注销页面并销毁当前activity
                Intent intent = new Intent(loginActivity.this,
                        MainActivity.class);
                intent.putExtra("Username",username);
                intent.putExtra("Role",role);
                startActivity(intent);
                finish();
            }
        }
    }

    //初始化本地数据库
    private void initDatabase(){
        //初始化数据库
        mDBOpenHelper = new DBOpenHelper(getApplicationContext());
        mDBOpenHelper.getWritableDatabase();
        mDBOpenHelper.deleteAll2();
        mDBOpenHelper.add2("admin","SmartFarm2020",0);
        mDBOpenHelper.add2("user","2020SmartFarm",1);
        data=mDBOpenHelper.getAllData2();
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
