package com.att.m2x.client.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.datasource.Blueprint;
import com.att.m2x.client.internal.resource.PaginatedResource;


public class BlueprintResource extends PaginatedResource<Blueprint> {

    public BlueprintResource(String baseUrl, HttpClient client, ObjectMapper mapper) {
        super(baseUrl, client, mapper);
    }

    //no other operations

}

