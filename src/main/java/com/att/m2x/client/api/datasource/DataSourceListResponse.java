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

    public void setDataSources(List<DataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}

