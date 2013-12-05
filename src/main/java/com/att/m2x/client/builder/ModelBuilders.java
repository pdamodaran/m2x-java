package com.att.m2x.client.builder;

import com.att.m2x.client.builder.model.DataSourceBuilder;
import com.att.m2x.client.builder.model.KeyBuilder;
import com.att.m2x.client.builder.model.TriggerBuilder;


public final class ModelBuilders {

    public static DataSourceBuilder newDataSource() {
        return new DataSourceBuilder();
    }

    public static KeyBuilder newKey() {
        return new KeyBuilder();
    }

    public static TriggerBuilder newTrigger() {
        return new TriggerBuilder();
    }

}

