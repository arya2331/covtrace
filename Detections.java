package com.example.myapplication382.model;

public class Detections {
    private int id;
    private String name;
    private String address;
    private  String time;

    public Detections( String name, String address, String time) {

        this.name = name;
        this.address = address;
        this.time = time;
    }

    public Detections( int id,String name, String address, String time) {
        this.id=id;
        this.name = name;
        this.address = address;
        this.time = time;
    }

    public Detections()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
