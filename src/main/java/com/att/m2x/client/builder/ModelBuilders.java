package com.att.m2x.client.builder;

import com.att.m2x.client.builder.model.DataSourceBuilder;
import com.att.m2x.client.builder.model.KeyBuilder;
import com.att.m2x.client.builder.model.LocationBuilder;
import com.att.m2x.client.builder.model.TriggerBuilder;


public final class ModelBuilders {

    public static DataSourceBuilder blueprint() {
        return new DataSourceBuilder();
    }

    public static DataSourceBuilder dataSource() {
        return new DataSourceBuilder();
    }

    public static DataSourceBuilder batch() {
        return new DataSourceBuilder();
    }

    public static KeyBuilder key() {
        return new KeyBuilder();
    }

    public static TriggerBuilder trigger() {
        return new TriggerBuilder();
    }

    public static LocationBuilder location() {
        return new LocationBuilder();
    }

}

