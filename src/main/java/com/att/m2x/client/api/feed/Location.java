package com.att.m2x.client.api.feed;

import java.util.Date;
import java.util.List;


public class Location {

    private String name;
    private String latitude;
    private String longitude;
    private String elevation;
    private Date timestamp;
    private List<Waypoint> waypoints;

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

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

}

