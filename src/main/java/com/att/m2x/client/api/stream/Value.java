package com.att.m2x.client.api.stream;

import java.util.Date;


public class Value {

    private String value;
    private Date at;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

}

