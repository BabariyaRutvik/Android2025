package com.example.fakestore.POJO;

public class Rating
{
    private double rate;
    private int count;


    // creating constructor
    public Rating(double rate, int count) {
        this.rate = rate;
        this.count = count;
    }
    // getter and setter
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

}
