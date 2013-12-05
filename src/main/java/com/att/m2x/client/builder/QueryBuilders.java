package com.att.m2x.client.builder;

public class QueryBuilders {

    public static PaginationParamBuilder page(int page) {
        return new PaginationParamBuilder().page(page);
    }

    public static PaginationParamBuilder limit(int limit) {
        return new PaginationParamBuilder().limit(limit);
    }

}
