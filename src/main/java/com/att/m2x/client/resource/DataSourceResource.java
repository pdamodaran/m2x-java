package com.att.m2x.client.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.datasource.DataSource;
import com.att.m2x.client.api.datasource.DataSourceListResponse;
import com.att.m2x.client.api.datasource.DataSourceUpdateBuilder;
import com.att.m2x.client.internal.BasicOperation;


public class DataSourceResource extends BasicOperation<DataSource, DataSourceListResponse, DataSourceUpdateBuilder> {

    public DataSourceResource(String baseUrl, HttpClient client, ObjectMapper mapper) {
        super(baseUrl, client, mapper);
    }

    //no other operations

}

