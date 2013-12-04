package com.att.m2x.client.api.feed;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LogEntry {

    private String path;
    @JsonProperty("http_method") private String method;
    private int status;
    private Date at;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

}

