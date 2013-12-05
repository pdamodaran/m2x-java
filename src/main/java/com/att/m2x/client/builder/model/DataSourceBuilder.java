package com.att.m2x.client.builder.model;

import com.att.m2x.client.api.Visibility;


public class DataSourceBuilder extends UpdateBuilder {

    public DataSourceBuilder name(String name) {
        data.put("name", name);
        return this;
    }

    public DataSourceBuilder description(String description) {
        data.put("description", description);
        return this;
    }

    public DataSourceBuilder visibility(Visibility visibility) {
        data.put("visibility", visibility);
        return this;
    }

    public DataSourceBuilder tags(String... tags) {
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

}

