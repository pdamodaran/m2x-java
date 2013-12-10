package com.att.m2x.client.internal.deserializer;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = BasketDeserializer.class)
public class ListResponse<T> {

    private List<T> data;

    public ListResponse(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

}

