package com.example.smartfarm2.DataBase;

public class Farm {
    int Temp1;//温度1
    int Temp2;//温度2
    int Temp3;//温度3
    int Temp4;//温度4
    int Temp5;//温度5


    int airHumidity1;//空气湿度1
    int airHumidity2;//空气湿度2

    int lightIntensity;//光照强度
    int CO2;//二氧化碳浓度

    int soilMoisture1;//土壤湿度1
    int soilMoisture2;//土壤湿度2
    int soilMoisture3;//土壤湿度3

    int conductivity;//电导率
    int soilSalinity;//盐分

    public Farm(int Temp1,int Temp2,int Temp3,int Temp4,int Temp5,int soilMoisture1,int soilMoisture2,int soilMoisture3,int airHumidity1,int airHumidity2,int lightIntensity,int CO2,int conductivity,int soilSalinity){
        this.Temp1=Temp1;
        this.Temp2=Temp2;
        this.Temp3=Temp3;
        this.Temp4=Temp4;
        this.Temp5=Temp5;

        this.soilMoisture1=soilMoisture1;
        this.soilMoisture2=soilMoisture2;
        this.soilMoisture3=soilMoisture3;

        this.airHumidity1=airHumidity1;
        this.airHumidity2=airHumidity2;

        this.lightIntensity=lightIntensity;
        this.CO2=CO2;
        this.conductivity=conductivity;
        this.soilSalinity=soilSalinity;
    }

    public int getTemp1() {
        return Temp1;
    }

    public void setTemp1(int temp1) {
        Temp1 = temp1;
    }

    public int getTemp2() {
        return Temp2;
    }

    public void setTemp2(int temp2) {
        Temp2 = temp2;
    }

    public int getTemp3() {
        return Temp3;
    }

    public void setTemp3(int temp3) {
        Temp3 = temp3;
    }

    public int getTemp4() {
        return Temp4;
    }

    public void setTemp4(int temp4) {
        Temp4 = temp4;
    }

    public int getTemp5() {
        return Temp5;
    }

    public void setTemp5(int temp5) {
        Temp5 = temp5;
    }

    public int getAirHumidity1() {
        return airHumidity1;
    }

    public void setAirHumidity1(int airHumidity1) {
        this.airHumidity1 = airHumidity1;
    }

    public int getAirHumidity2() {
        return airHumidity2;
    }

    public void setAirHumidity2(int airHumidity2) {
        this.airHumidity2 = airHumidity2;
    }

    public int getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(int lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public int getCO2() {
        return CO2;
    }

    public void setCO2(int CO2) {
        this.CO2 = CO2;
    }

    public int getSoilMoisture1() {
        return soilMoisture1;
    }

    public void setSoilMoisture1(int soilMoisture1) {
        this.soilMoisture1 = soilMoisture1;
    }

    public int getSoilMoisture2() {
        return soilMoisture2;
    }

    public void setSoilMoisture2(int soilMoisture2) {
        this.soilMoisture2 = soilMoisture2;
    }

    public int getSoilMoisture3() {
        return soilMoisture3;
    }

    public void setSoilMoisture3(int soilMoisture3) {
        this.soilMoisture3 = soilMoisture3;
    }

    public int getConductivity() {
        return conductivity;
    }

    public void setConductivity(int conductivity) {
        this.conductivity = conductivity;
    }

    public int getSoilSalinity() {
        return soilSalinity;
    }

    public void setSoilSalinity(int soilSalinity) {
        this.soilSalinity = soilSalinity;
    }
}
