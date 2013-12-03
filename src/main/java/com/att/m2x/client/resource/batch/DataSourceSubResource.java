package com.att.m2x.client.resource.batch;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.datasource.DataSource;
import com.att.m2x.client.api.datasource.DataSourceListResponse;
import com.att.m2x.client.internal.ExecutableResource;


public class DataSourceSubResource extends ExecutableResource {

    public DataSourceSubResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    public DataSourceListResponse dataSources() {
        return execute(prepare().get()).status(200).as(DataSourceListResponse.class);
    }

    public DataSource addDataSource(String serial) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("serial", serial);

        return execute(prepare().post().body(data)).status(201).as(DataSource.class);
    }

}

