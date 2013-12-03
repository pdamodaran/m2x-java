package com.att.m2x.client.api.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DataSourceInfo {

    private int total;
    private int registered;
    private int unregistered;

    public DataSourceInfo(@JsonProperty("total") int total,
                          @JsonProperty("registered") int registered,
                          @JsonProperty("unregistered") int unregistered) {
        this.total = total;
        this.registered = registered;
        this.unregistered = unregistered;
    }

    public int getTotal() {
        return total;
    }

    public int getRegistered() {
        return registered;
    }

    public int getUnregistered() {
        return unregistered;
    }

}

