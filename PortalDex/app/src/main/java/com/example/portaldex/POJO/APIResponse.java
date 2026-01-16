package com.example.portaldex.POJO;

import java.util.List;

public class APIResponse {
    private Info info;
    private List<Character> results;

    // constructor
    public APIResponse(Info info, List<Character> results) {
        this.info = info;
        this.results = results;
    }

    // getter and setter
    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
    public List<Character> getResults() {
        return results;
    }
    public void setResults(List<Character> results) {
        this.results = results;
    }


}
