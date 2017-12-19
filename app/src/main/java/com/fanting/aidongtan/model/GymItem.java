package com.fanting.aidongtan.model;

import android.graphics.Bitmap;

/**
 * Created by foryoung on 2017/12/14.
 */

public class GymItem {

    private Bitmap logo;
    private String name;
    private String distance;
    private String seat;

    public GymItem(Bitmap logo, String name, String distance, String seat) {
        this.logo = logo;
        this.name = name;
        this.distance = distance;
        this.seat = seat;
    }

    public GymItem() {
    }


    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
