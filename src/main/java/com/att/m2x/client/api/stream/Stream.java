package com.att.m2x.client.api.stream;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Stream {

    private String id;
    private String name;
    private String value;
    @JsonProperty("latest_value_at") private Date latestValueAt;
    private String min;
    private String max;
    private Unit unit;
    private String url;
    private Date created;
    private Date updated;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Date getLatestValueAt() {
        return latestValueAt;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

}

