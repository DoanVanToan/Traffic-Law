package com.toandoan.luatgiaothong.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 16/05/2017.
 */

public class LocationModel {
    @SerializedName("lat")
    private double mLat;
    @SerializedName("lng")
    private double mLng;
    @SerializedName("address")
    private String mAddress;

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLng() {
        return mLng;
    }

    public void setLng(double lng) {
        mLng = lng;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }
}
