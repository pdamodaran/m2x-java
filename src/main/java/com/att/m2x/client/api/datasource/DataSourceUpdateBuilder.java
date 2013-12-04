package com.att.m2x.client.api.datasource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.att.m2x.client.api.Visibility;
import com.att.m2x.client.internal.UpdateBuilder;


public class DataSourceUpdateBuilder implements UpdateBuilder {

    private Map<String, Object> data = new HashMap<String, Object>();

    private DataSourceUpdateBuilder() {
        //empty
    }


    public Map<String, Object> build() {
        return Collections.unmodifiableMap(data);
    }

    public DataSourceUpdateBuilder name(String name) {
        data.put("name", name);
        return this;
    }

    public DataSourceUpdateBuilder description(String description) {
        data.put("description", description);
        return this;
    }

    public DataSourceUpdateBuilder visibility(Visibility visibility) {
        data.put("visibility", visibility);
        return this;
    }

    public DataSourceUpdateBuilder tags(String... tags) {
        StringBuilder buffer = new StringBuilder();
        for (String tag : tags) {
            if (buffer.length() != 0) {
                buffer.append(",");
            }
            buffer.append(tag);
        }
        data.put("tags", buffer.toString());
        return this;
    }

    public static DataSourceUpdateBuilder builder() {
        return new DataSourceUpdateBuilder();
    }

}

