package com.example.disneyverse.POJO;

import java.util.List;

public class DisneyResponce {

    private Info info;
    private List<Character> data;

    // constructor
    public DisneyResponce(Info info, List<Character> data){
        this.info = info;
        this.data = data;

    }
    public Info getInfo() {
        return info;
    }
    public void setInfo(Info info) {
        this.info = info;
    }
    public List<Character> getData() {
        return data;
    }
    public void setData(List<Character> data) {
        this.data = data;
    }

}
