package com.att.m2x.client.builder.model;

import com.att.m2x.client.api.Status;
import com.att.m2x.client.api.trigger.Condition;


public class TriggerBuilder extends UpdateBuilder {

    public TriggerBuilder stream(String stream) {
        data.put("stream", stream);
        return this;
    }

    public TriggerBuilder condition(Condition condition) {
        data.put("condition", condition);
        return this;
    }

    public TriggerBuilder value(String value) {
        data.put("value", value);
        return this;
    }

    public TriggerBuilder callback(String callback) {
        data.put("callback_url", callback);
        return this;
    }

    public TriggerBuilder status(Status status) {
        data.put("status", status);
        return this;
    }

    public TriggerBuilder name(String name) {
        data.put("name", name);
        return this;
    }

}

