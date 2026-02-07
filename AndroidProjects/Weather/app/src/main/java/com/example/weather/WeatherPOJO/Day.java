package com.example.weather.WeatherPOJO;

public class Day
{
    private double maxtemp;
    private double mintemp;
    private double avgtemp;
    private double maxwind;
    private double totalprecip;
    private int avghumidity;
    private Condition condition;

    // Getter and setter methods for day properties

    public double getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(double maxtemp) {
        this.maxtemp = maxtemp;
    }

    public double getMintemp() {
        return mintemp;
    }

    public void setMintemp(double mintemp) {
        this.mintemp = mintemp;
    }

    public double getAvgtemp() {
        return avgtemp;
    }

    public void setAvgtemp(double avgtemp) {
        this.avgtemp = avgtemp;
    }

    public double getMaxwind() {
        return maxwind;
    }

    public void setMaxwind(double maxwind) {
        this.maxwind = maxwind;
    }

    public double getTotalprecip() {
        return totalprecip;
    }

    public void setTotalprecip(double totalprecip) {
        this.totalprecip = totalprecip;
    }

    public int getAvghumidity() {
        return avghumidity;
    }

    public void setAvghumidity(int avghumidity) {
        this.avghumidity = avghumidity;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
