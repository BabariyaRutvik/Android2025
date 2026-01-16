package com.example.portaldex.POJO;

import java.io.Serializable;

public class Location implements Serializable
{
    private String name;
    private String url;

     // constructor
    public Location(String name, String url) {
         this.name = name;
         this.url = url;
    }
    // getter and setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}