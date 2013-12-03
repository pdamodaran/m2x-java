package com.att.m2x.client.internal;

import java.lang.reflect.ParameterizedType;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.internal.ExecutableResource;
import com.att.m2x.client.internal.UpdateBuilder;


public class BasicOperation<E, ES, UO extends UpdateBuilder> extends ExecutableResource {

    private final Class<E> E_TYPE;
    private final Class<ES> ES_TYPE;

    @SuppressWarnings("unchecked")
    public BasicOperation(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);

        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.E_TYPE= (Class<E>) genericSuperclass.getActualTypeArguments()[0];
        this.ES_TYPE= (Class<ES>) genericSuperclass.getActualTypeArguments()[1];
    }

    public E get(String id) {
        return execute(prepare().get(id)).status(200).as(E_TYPE);
    }

    public ES list() {
        return execute(prepare().get()).status(200).as(ES_TYPE);
    }

    public E create(UO data) {
        return execute(prepare().post().body(data)).status(201).as(E_TYPE);
    }

    public void update(String id, UO data) {
        execute(prepare().put(id).body(data)).status(204);
    }

    public void delete(String id) {
        execute(prepare().delete(id)).status(204);
    }

}

