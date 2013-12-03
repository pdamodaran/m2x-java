package com.att.m2x.client.api.trigger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.att.m2x.client.api.Status;
import com.att.m2x.client.internal.UpdateBuilder;


public class TriggerUpdateBuilder implements UpdateBuilder {

    private Map<String, Object> data = new HashMap<String, Object>();

    private TriggerUpdateBuilder() {
        //empty
    }

    @Override
    public Map<String, Object> build() {
        return Collections.unmodifiableMap(data);
    }

    public TriggerUpdateBuilder stream(String stream) {
        data.put("stream", stream);
        return this;
    }

    public TriggerUpdateBuilder condition(Condition condition) {
        data.put("condition", condition);
        return this;
    }

    public TriggerUpdateBuilder value(String value) {
        data.put("value", value);
        return this;
    }

    public TriggerUpdateBuilder callback(String callback) {
        data.put("callback_url", callback);
        return this;
    }

    public TriggerUpdateBuilder status(Status status) {
        data.put("status", status);
        return this;
    }

    public static TriggerUpdateBuilder builder() {
        return new TriggerUpdateBuilder();
    }

}

