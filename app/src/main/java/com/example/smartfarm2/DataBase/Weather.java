package com.example.smartfarm2.DataBase;

public class Weather {
    private String weather;
    private String wenhou;
    private String weatherinfo;

    public Weather(String weatherinfo,String weather,String wenhou){
        this.weatherinfo=weatherinfo;
        this.weather=weather;
        this.wenhou=wenhou;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWenhou() {
        return wenhou;
    }

    public void setWenhou(String wenhou) {
        this.wenhou = wenhou;
    }

    public String getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(String weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    @Override
    public String toString() {
        return "Weather{"+
                "weatherinfo='"+weatherinfo+'\''+
                "weather='"+weather+'\''+
                ",wenhou='"+wenhou+'\''+
                "}";
    }
}
