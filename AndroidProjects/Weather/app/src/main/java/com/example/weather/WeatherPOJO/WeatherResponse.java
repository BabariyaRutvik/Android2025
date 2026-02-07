package com.example.weather.WeatherPOJO;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse
{
    @SerializedName("location")
    private Location location;

    @SerializedName("current")
    private Current current;

    @SerializedName("forecast")
    private Forecast forecast;

    // Getter and setter methods for location and current

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Current getCurrent() {
        return current;
    }
    public void setCurrent(Current current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
