package com.att.m2x.client.api.feed;


import java.util.Date;


public class Waypoint {

    private String name;
    private String latitude;
    private String longitude;
    private String elevation;
    private Date timestamp;

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getElevation() {
        return elevation;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
