package com.att.m2x.client.internal.resource;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;


public class ListedResource<E> extends BasicOperation<E> {

    public ListedResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    public List<E> list() {
        return execute(prepare().get()).status(200).list(E_TYPE);
    }

}
