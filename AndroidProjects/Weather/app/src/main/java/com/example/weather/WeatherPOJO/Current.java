package com.example.weather.WeatherPOJO;

import com.google.gson.annotations.SerializedName;

public class Current
{
    private double temperature;
    private double feels_like;
    private  int humidity;
    private int pressure;
    private int visibility;

    private int uv_index;
    private double wind_speed;
    private  int wind_degree;
    private String wind_direction;
    private  int is_day;
    private String last_updated;
    private Condition condition;


    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getUv_index() {
        return uv_index;
    }

    public void setUv_index(int uv_index) {
        this.uv_index = uv_index;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getWind_degree() {
        return wind_degree;
    }

    public void setWind_degree(int wind_degree) {
        this.wind_degree = wind_degree;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public int getIs_day() {
        return is_day;
    }

    public void setIs_day(int is_day) {
        this.is_day = is_day;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
