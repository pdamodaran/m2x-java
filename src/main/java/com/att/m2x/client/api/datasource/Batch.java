package com.att.m2x.client.api.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Batch extends CommonDataSource {

    @JsonProperty("datasources")
    protected DataSourceInfo dataSources;

    public DataSourceInfo getDataSources() {
        return dataSources;
    }

    public void setDataSources(DataSourceInfo dataSources) {
        this.dataSources = dataSources;
    }

}

