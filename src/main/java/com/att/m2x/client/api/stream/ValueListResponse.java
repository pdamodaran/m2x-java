package com.att.m2x.client.api.stream;

import java.util.Date;
import java.util.List;


public class ValueListResponse {

    private List<Value> values;
    private Date start;
    private Date end;
    private int limit;

    public List<Value> getValues() {
        return values;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public int getLimit() {
        return limit;
    }

}

