package com.att.m2x.client.api.datasource;

import java.util.Date;
import java.util.List;

import com.att.m2x.client.api.Status;
import com.att.m2x.client.api.Visibility;


public abstract class CommonDataSource {

    protected String id;
    protected String name;
    protected String description;
    protected Visibility visibility;
    protected String serial;
    protected Status status;
    protected String feed;
    protected List<String> tags;
    protected String url;
    protected String key;
    protected Date created;
    protected Date updated;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public String getSerial() {
        return serial;
    }

    public Status getStatus() {
        return status;
    }

    public String getFeed() {
        return feed;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

}

