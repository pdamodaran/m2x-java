package com.att.m2x.client.api.datasource;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DataSourceListResponse {

    @JsonProperty("datasources") private List<DataSource> dataSources;
    @JsonProperty("current_page") private int currentPage;
    private int limit;
    private int pages;
    private int total;


    public List<DataSource> getDataSources() {
        return dataSources;
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

