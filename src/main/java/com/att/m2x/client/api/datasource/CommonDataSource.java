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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getCreated() {
        return new Date(created.getTime());
    }

    public void setCreated(Date created) {
        this.created = new Date(created.getTime());
    }

    public Date getUpdated() {
        return new Date(updated.getTime());
    }

    public void setUpdated(Date updated) {
        this.updated = new Date(updated.getTime());
    }

}

