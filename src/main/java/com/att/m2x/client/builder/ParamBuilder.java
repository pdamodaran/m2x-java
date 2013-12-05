package com.att.m2x.client.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class ParamBuilder {

    protected Map<String, String> entries = new HashMap<String, String>();

    public List<String> apply() {
        List<String> params = new ArrayList<String>(entries.size() * 2);
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            params.add(entry.getKey());
            params.add(entry.getValue());
        }

        return params;
    }

}
