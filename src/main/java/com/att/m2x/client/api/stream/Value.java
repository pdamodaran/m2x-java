package com.att.m2x.client.api.stream;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Value {

    private String value;
    private Date at;

    public Value(String value) {
        this.value = value;
    }

    @JsonCreator
    public Value(@JsonProperty("value") String value, @JsonProperty("at") Date at) {
        this.value = value;
        this.at = at;
    }

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

