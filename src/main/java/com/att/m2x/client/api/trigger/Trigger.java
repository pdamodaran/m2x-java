package com.att.m2x.client.api.trigger;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.att.m2x.client.api.Status;


public class Trigger {

    private String id;
    private String name;
    private String stream;
    private Condition condition;
    private String value;
    private String unit;
    @JsonProperty("callback_url") private String callback;
    private String url;
    private Status status;
    private Date created;
    private Date updated;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStream() {
        return stream;
    }

    public Condition getCondition() {
        return condition;
    }

    public String getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getCallback() {
        return callback;
    }

    public String getUrl() {
        return url;
    }

    public Status getStatus() {
        return status;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

}

