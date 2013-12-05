package com.att.m2x.client.builder;


public class PaginationParamBuilder extends ParamBuilder {

    public PaginationParamBuilder page(int i) {
        entries.put("page", String.valueOf(i));
        return this;
    }

    public PaginationParamBuilder limit(int i) {
        entries.put("limit", String.valueOf(i));
        return this;
    }

}
