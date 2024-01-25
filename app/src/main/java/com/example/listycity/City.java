package com.example.listycity;

import java.io.Serializable;

public class City implements Serializable {
    private String cityName ;
    private  String province ;

    public City(String name , String province){
        this.cityName = name ;
        this.province = province ;
    }

    public String getCityName() {
        return cityName;
    }

    public String getProvince() {
        return province;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
