package com.att.m2x.client.api.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.att.m2x.client.api.DataSourceInfo;


public class Batch extends CommonDataSource {

    @JsonProperty("datasources") protected DataSourceInfo dataSources;

    public DataSourceInfo getDataSources() {
        return dataSources;
    }

}

