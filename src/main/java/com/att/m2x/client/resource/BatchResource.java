package com.att.m2x.client.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.datasource.Batch;
import com.att.m2x.client.internal.resource.PaginatedResource;
import com.att.m2x.client.resource.batch.DataSourceSubResource;


public class BatchResource extends PaginatedResource<Batch> {

    public BatchResource(String baseUrl, HttpClient client, ObjectMapper mapper) {
        super(baseUrl, client, mapper);
    }

    public DataSourceSubResource batch(String id) {
        return drill(id + "/datasources", DataSourceSubResource.class);
    }

}

