package com.att.m2x.client.api;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.att.m2x.client.internal.deserializer.Basket;
import com.att.m2x.client.internal.deserializer.PageDeserializer;


@JsonDeserialize(using = PageDeserializer.class)
public class Page<T> extends Basket<T> {

    private int currentPage;
    private int limit;
    private int pages;
    private int total;

    public Page(List<T> data, int currentPage, int limit, int pages, int total) {
        super(data);
        this.currentPage = currentPage;
        this.limit = limit;
        this.pages = pages;
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getLimit() {
        return limit;
    }

    public int getPages() {
        return pages;
    }

    public int getTotal() {
        return total;
    }

}

