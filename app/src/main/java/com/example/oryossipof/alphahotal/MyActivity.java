package com.example.oryossipof.alphahotal;


import java.io.Serializable;

public class MyActivity implements Serializable {

    public String title;
    public String location;
    public String hours;
    public String time;
    public String info;

    public MyActivity(String title,String location, String hours,String time,String info) {
        this.title = title;
        this.location = location;
        this.hours = hours;
        this.time = time;
        this.info = info;
    }
}
