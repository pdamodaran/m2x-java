package com.att.m2x.client.resource.feed;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.trigger.Trigger;
import com.att.m2x.client.api.trigger.TriggerListRespond;
import com.att.m2x.client.api.trigger.TriggerUpdateBuilder;
import com.att.m2x.client.internal.BasicOperation;


public class TriggerSubResource extends BasicOperation<Trigger, TriggerListRespond, TriggerUpdateBuilder> {

    public TriggerSubResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    public void test(String id) {
        execute(prepare().get(id + "/test")).status(204);
    }

}

