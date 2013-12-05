package com.att.m2x.client.internal.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.Page;
import com.att.m2x.client.builder.PaginationParamBuilder;
import com.att.m2x.client.builder.ParamBuilder;


public class PaginatedResource<E> extends BasicOperation<E> {

    public PaginatedResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    public Page<E> list() {
        return execute(prepare().get()).status(200).page(E_TYPE);
    }

    public Page<E> list(ParamBuilder pb) {
        return execute(prepare().get().params(pb)).status(200).page(E_TYPE);
    }

}
