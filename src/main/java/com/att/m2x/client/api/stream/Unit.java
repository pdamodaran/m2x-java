package com.att.m2x.client.api.stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Unit {

    private String label;
    private String symbol;

    @JsonCreator
    public Unit(@JsonProperty("label") String label, @JsonProperty("symbol") String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    public String getLabel() {
        return label;
    }

    public String getSymbol() {
        return symbol;
    }

}

