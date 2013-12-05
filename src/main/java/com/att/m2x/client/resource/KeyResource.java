package com.att.m2x.client.resource;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.key.Key;
import com.att.m2x.client.internal.resource.ListedResource;


public class KeyResource extends ListedResource<Key> {

    public KeyResource(String baseUrl, HttpClient client, ObjectMapper mapper) {
        super(baseUrl, client, mapper);
    }

    public List<Key> list(String feed) {
        return execute(prepare().get().params("feed", feed)).status(200).list(E_TYPE);
    }

    public Key regenerate(String keyId) {
        return execute(prepare().post(keyId + "/regenerate")).status(201).as(Key.class);
    }

}

