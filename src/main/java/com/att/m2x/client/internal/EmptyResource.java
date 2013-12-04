package com.att.m2x.client.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.exception.ClientException;


public class EmptyResource {

    protected final String path;
    protected final HttpClient client;
    protected final ObjectMapper mapper;

    public EmptyResource(String path, HttpClient client, ObjectMapper mapper) {
        this.path = path;
        this.client = client;
        this.mapper = mapper;
    }

    public <T> T drill(String path, Class<T> resource) {
        try {
            return resource
                    .getConstructor(String.class, HttpClient.class, ObjectMapper.class)
                    .newInstance(this.path + "/" +path, client, mapper);
        } catch (Exception ex) {
            throw new ClientException("Not valid resource");
        }
    }

}

