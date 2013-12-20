package com.att.m2x.client.api.datasource;


import com.fasterxml.jackson.annotation.JsonProperty;


public class DataSource extends CommonDataSource {

    private String batch;
    @JsonProperty("batch_name")
    private String batchName;

    public String getBatch() {
        return batch;
    }

    public String getBatchName() {
        return batchName;
    }

}

