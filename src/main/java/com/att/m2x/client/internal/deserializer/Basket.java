package com.att.m2x.client.internal.deserializer;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = BasketDeserializer.class)
public class Basket<T> {

    private List<T> data;

    public Basket(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

}
