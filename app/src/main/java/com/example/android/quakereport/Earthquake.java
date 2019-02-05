package com.example.android.quakereport;

public class Earthquake {

    private String mLocation;
    private double mMag;
    private long mTimeInMilliSeconds;
    private String mUrl;


    public Earthquake(String location, double magnitude, long TimeInMilliSeconds, String url) {
        mLocation = location;
        mMag = magnitude;
        mTimeInMilliSeconds = TimeInMilliSeconds;
        mUrl = url;
    }

    public String getLocation() {
        return mLocation;
    }

    public double getMag() {
        return mMag;
    }

    public long getTimeInMilliSeconds() {
        return mTimeInMilliSeconds;
    }

    public String getUrl() {
        return mUrl;
    }

}
