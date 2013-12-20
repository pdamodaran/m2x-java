package com.att.m2x.client.api.feed;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.att.m2x.client.api.DataSourceInfo;


//see @Feed
@Deprecated
public class BatchFeed extends Feed {

    @JsonProperty("datasources") private DataSourceInfo dataSources;

    public DataSourceInfo getDataSources() {
        return dataSources;
    }

}

