package com.att.m2x.client.builder.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class UpdateBuilder {

    protected Map<String, Object> data = new HashMap<String, Object>();

    public Map<String, Object> build() {
        return Collections.unmodifiableMap(data);
    }

}

