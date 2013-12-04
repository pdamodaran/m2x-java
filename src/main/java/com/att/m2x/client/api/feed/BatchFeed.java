package com.att.m2x.client.api.feed;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.att.m2x.client.api.DataSourceInfo;


public class BatchFeed extends CommonFeed {

    @JsonProperty("datasources") private DataSourceInfo dataSources;

    public DataSourceInfo getDataSources() {
        return dataSources;
    }

}

