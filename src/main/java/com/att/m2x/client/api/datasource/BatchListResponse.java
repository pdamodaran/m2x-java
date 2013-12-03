package com.att.m2x.client.api.datasource;

import java.util.List;


public class BatchListResponse {

    private List<Batch> batches;

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

}

