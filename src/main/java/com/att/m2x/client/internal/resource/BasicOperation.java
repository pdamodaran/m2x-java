package com.att.m2x.client.internal.resource;

import java.lang.reflect.ParameterizedType;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.builder.model.UpdateBuilder;


public class BasicOperation<E> extends ExecutableResource {

    protected final Class<E> E_TYPE;

    @SuppressWarnings("unchecked")
    public BasicOperation(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);

        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.E_TYPE = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    public E get(String id) {
        return execute(prepare().get(id)).status(200).as(E_TYPE);
    }

    public E create(UpdateBuilder data) {
        return execute(prepare().post().body(data)).status(201).as(E_TYPE);
    }

    public void update(String id, UpdateBuilder data) {
        execute(prepare().put(id).body(data)).status(204);
    }

    public void delete(String id) {
        execute(prepare().delete(id)).status(204);
    }

}

