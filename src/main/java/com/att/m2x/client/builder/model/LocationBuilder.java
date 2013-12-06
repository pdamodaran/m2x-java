package com.att.m2x.client.builder.model;


public class LocationBuilder extends UpdateBuilder {

    public LocationBuilder name(String name) {
        data.put("name", name);
        return this;
    }

    public LocationBuilder latitude(String latitude) {
        data.put("latitude", latitude);
        return this;
    }

    public LocationBuilder longitude(String name) {
        data.put("longitude", name);
        return this;
    }

    public LocationBuilder elevation(String name) {
        data.put("elevation", name);
        return this;
    }

}
