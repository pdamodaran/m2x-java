package com.att.m2x.client.api.datasource;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BlueprintListResponse {

    private List<Blueprint> blueprints;
    @JsonProperty("current_page") private int currentPage;
    private int limit;
    private int pages;
    private int total;

    public List<Blueprint> getBlueprints() {
        return blueprints;
    }

    public void setBlueprints(List<Blueprint> blueprints) {
        this.blueprints = blueprints;
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

