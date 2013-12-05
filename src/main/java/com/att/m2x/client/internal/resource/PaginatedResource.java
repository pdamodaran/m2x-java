package com.att.m2x.client.internal.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.Page;


public class PaginatedResource<E> extends BasicOperation<E> {

    public PaginatedResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    public Page<E> list() {
        return execute(prepare().get()).status(200).page(E_TYPE);
    }

    public Page<E> list(int page) {
        return execute(prepare().get().params("page", String.valueOf(page))).status(200).page(E_TYPE);
    }

    public Page<E> list(int page, int limit) {
        String[] params = new String[] {"page", String.valueOf(page), "limit", String.valueOf(limit)};
        return execute(prepare().get().params(params)).status(200).page(E_TYPE);
    }

}
