package com.example.smartfarm2.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by slatejack 2019/5/5，现已经弃用，改用接口形式获取数据
 */


public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String TAG="天气数据库";
    private static final String TAG1="农场数据库";
    private static final String TAG2="用户数据库";
    /**
     * 声明一个AndroidSDK自带的数据库变量db
     */
    private SQLiteDatabase db1;

    /**
     * 写一个这个类的构造函数，参数为上下文context，所谓上下文就是这个类所在包的路径
     * 指明上下文，数据库名，工厂默认空值，版本号默认从1开始
     * super(context,"db_test",null,1);
     * 把数据库设置成可写入状态，除非内存已满，那时候会自动设置为只读模式
     * 不过，以现如今的内存容量，估计一辈子也见不到几次内存占满的状态
     * db = getReadableDatabase();
     */
    public DBOpenHelper(Context context){
        super(context,"db_SmartFarm2",null,1);
        db1 = getReadableDatabase();
    }

    /**
     * 重写两个必须要重写的方法，因为class DBOpenHelper extends SQLiteOpenHelper
     * 而这两个方法是 abstract 类 SQLiteOpenHelper 中声明的 abstract 方法
     * 所以必须在子类 DBOpenHelper 中重写 abstract 方法
     * 想想也是，为啥规定这么死必须重写？
     * 因为，一个数据库表，首先是要被创建的，然后免不了是要进行增删改操作的
     * 所以就有onCreate()、onUpgrade()两个方法
     * @param db1
     */
    @Override
    public void onCreate(SQLiteDatabase db1){
        db1.execSQL("CREATE TABLE IF NOT EXISTS weatherdata(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "weatherinfo TEXT,"+
                "weather TEXT,"+
                "wenhou TEXT)");

        db1.execSQL("CREATE TABLE IF NOT EXISTS farmdata(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Temp1 INTEGER,"+
                "Temp2 INTEGER,"+
                "Temp3 INTEGER,"+
                "Temp4 INTEGER,"+
                "Temp5 INTEGER,"+

                "soilMoisture1 INTEGER,"+
                "soilMoisture2 INTEGER,"+
                "soilMoisture3 INTEGER,"+

                "airHumidity1 INTEGER,"+
                "airHumidity2 INTEGER,"+

                "lightIntensity INTEGER,"+
                "CO2 INTEGER,"+
                "conductivity INTEGER,"+
                "soilSalinity INTEGER)");

        db1.execSQL("CREATE TABLE IF NOT EXISTS userdata("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT,"+
                "userpass TEXT,"+
                "role INTEGER)");

        Log.i(TAG, "创建数据表成功");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion){
        db1.execSQL("DROP TABLE IF EXISTS weatherdata");
        db1.execSQL("DROP TABLE IF EXISTS farmdata");
        db1.execSQL("DROP TABLE IF EXISTS userdata");
        onCreate(db1);
    }
    /**
     * 接下来写自定义的增删改查方法
     * 这些方法，写在这里归写在这里，以后不一定都用
     * add()
     * delete()
     * update()
     * getAllData()
     */
    public void add(String weatherinfo,String weather,String wenhou){
        db1.execSQL("INSERT INTO weatherdata (weatherinfo,weather,wenhou) VALUES(?,?,?)",new Object[]{weatherinfo,weather,wenhou});
    }
    public void add1(int Temp1,int Temp2,int Temp3,int Temp4,int Temp5,int soilMoisture1,int soilMoisture2,int soilMoisture3,int airHumidity1,int airHumidity2,int lightIntensity,int CO2,int conductivity,int soilSalinity){
        db1.execSQL("INSERT INTO farmdata (Temp1,Temp2,Temp3,Temp4,Temp5,soilMoisture1,soilMoisture2,soilMoisture3,airHumidity1,airHumidity2,lightIntensity,CO2,conductivity,soilSalinity) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",new Object[]{Temp1,Temp2,Temp3,Temp4,Temp5,soilMoisture1,soilMoisture2,soilMoisture3,airHumidity1,airHumidity2,lightIntensity,CO2,conductivity,soilSalinity});
    }
    public void add2(String username,String userpass,int role){
        db1.execSQL("INSERT INTO userdata(username,userpass,role) VALUES(?,?,?)",new Object[]{username,userpass,role});
    }
    //手动删除一条数据库的方法，不用
    public void delete(String weatherinfo,String weather,String wenhou){
        db1.execSQL("DELETE FROM weatherdata WHERE weather =? AND wenhou =?",new Object[]{weather,wenhou});
    }
    //手动修改数据库方法，暂时不用
    public void updata(String weatherinfo,String weather,String wenhou){
        db1.execSQL("UPDATE weatherdata SET weather = ? WHERE wenhou=? ",new Object[]{weather,wenhou});
    }
    //清空天气数据库，清理缓存时调用
    public void deleteAll(){
        db1.execSQL("DELETE FROM weatherdata ");
    }
    public void deleteAll1(){db1.execSQL("DELETE FROM farmdata");}
    public void deleteAll2(){db1.execSQL("DELETE FROM userdata");}

    /**
     * 前三个没啥说的，都是一套的看懂一个其他的都能懂了
     * 下面重点说一下查询表user全部内容的方法
     * 我们查询出来的内容，需要有个容器存放，以供使用，
     * 所以定义了一个ArrayList类的list
     * 有了容器，接下来就该从表中查询数据了，
     * 这里使用游标Cursor，这就是数据库的功底了，
     * 在Android中我就不细说了，因为我数据库功底也不是很厚，
     * 但我知道，如果需要用Cursor的话，第一个参数："表名"，中间5个：null，
     *                                                     最后是查询出来内容的排序方式："name DESC"
     * 游标定义好了，接下来写一个while循环，让游标从表头游到表尾
     * 在游的过程中把游出来的数据存放到list容器中
     * @return
     */
    public ArrayList<Weather> getAllData(){

        ArrayList<Weather> list = new ArrayList<Weather>();
        Cursor cursor = db1.query("weatherdata",null,null,null,null,null,"weather DESC");
        while(cursor.moveToNext()){
            String Weatherinfo= cursor.getString(cursor.getColumnIndex("weatherinfo"));
            String Weather = cursor.getString(cursor.getColumnIndex("weather"));
            String Wenhou=cursor.getString(cursor.getColumnIndex("wenhou"));
            list.add(new Weather(Weatherinfo,Weather,Wenhou));
        }
        return list;
    }

    public ArrayList<Farm> getAllData1(){
        ArrayList<Farm> list1 = new ArrayList<Farm>();
        Cursor cursor = db1.query("farmdata",null,null,null,null,null,"Temp1 DESC");
        while(cursor.moveToNext()){
            int Temp1=cursor.getInt(cursor.getColumnIndex("Temp1"));
            int Temp2=cursor.getInt(cursor.getColumnIndex("Temp2"));
            int Temp3=cursor.getInt(cursor.getColumnIndex("Temp3"));
            int Temp4=cursor.getInt(cursor.getColumnIndex("Temp4"));
            int Temp5=cursor.getInt(cursor.getColumnIndex("Temp5"));

            int soilMoisture1=cursor.getInt(cursor.getColumnIndex("soilMoisture1"));
            int soilMoisture2=cursor.getInt(cursor.getColumnIndex("soilMoisture2"));
            int soilMoisture3=cursor.getInt(cursor.getColumnIndex("soilMoisture3"));

            int airHumidity1=cursor.getInt(cursor.getColumnIndex("airHumidity1"));
            int airHumidity2=cursor.getInt(cursor.getColumnIndex("airHumidity2"));

            int lightIntensity=cursor.getInt(cursor.getColumnIndex("lightIntensity"));
            int CO2=cursor.getInt(cursor.getColumnIndex("CO2"));
            int conductivity=cursor.getInt(cursor.getColumnIndex("conductivity"));
            int soilSalinity=cursor.getInt(cursor.getColumnIndex("soilSalinity"));

            list1.add(new Farm(Temp1,Temp2,Temp3,Temp4,Temp5,soilMoisture1,soilMoisture2,soilMoisture3,airHumidity1,airHumidity2,lightIntensity,CO2,conductivity,soilSalinity));
        }
        return list1;
    }

    public ArrayList<User> getAllData2(){
        ArrayList<User> list2=new ArrayList<User>();
        Cursor cursor = db1.query("userdata",null,null,null,null,null,"_id DESC");
        while(cursor.moveToNext()){
            String username=cursor.getString(cursor.getColumnIndex("username"));
            String userpass=cursor.getString(cursor.getColumnIndex("userpass"));
            int role=cursor.getInt(cursor.getColumnIndex("role"));
            list2.add(new User(username,userpass,role));
        }
        return list2;
    }
}