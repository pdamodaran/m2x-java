package com.att.m2x.client.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.key.Key;
import com.att.m2x.client.api.key.KeyListResponse;
import com.att.m2x.client.api.key.KeyUpdateBuilder;
import com.att.m2x.client.internal.BasicOperation;


public class KeyResource extends BasicOperation<Key, KeyListResponse, KeyUpdateBuilder> {

    public KeyResource(String baseUrl, HttpClient client, ObjectMapper mapper) {
        super(baseUrl, client, mapper);
    }

    public KeyListResponse list(String feed) {
        return execute(prepare().get().params("feed",feed)).status(200).as(KeyListResponse.class);
    }

    public Key regenerate(String keyId) {
        return execute(prepare().post(keyId + "/regenerate")).status(201).as(Key.class);
    }

}

