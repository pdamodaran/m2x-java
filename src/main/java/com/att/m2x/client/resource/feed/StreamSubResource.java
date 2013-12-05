package com.att.m2x.client.resource.feed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.stream.Stream;
import com.att.m2x.client.api.stream.Unit;
import com.att.m2x.client.api.stream.Value;
import com.att.m2x.client.api.stream.ValueListResponse;
import com.att.m2x.client.builder.param.StreamValueParamBuilder;
import com.att.m2x.client.internal.resource.ExecutableResource;


public class StreamSubResource extends ExecutableResource {

    public StreamSubResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    public List<Stream> list() {
        return execute(prepare().get()).status(200).list(Stream.class);
    }

    public Stream create(String name, Unit unit) {
        //TODO: PK,04/12: check stream before create
        return execute(prepare().put(name).body(wrap(unit))).status(201).as(Stream.class);
    }

    public void update(String name, Unit unit) {
        //TODO: PK,04/12: check stream before update
        execute(prepare().put(name).body(wrap(unit))).status(204);
    }

    public Stream get(String name) {
        return execute(prepare().get(name)).status(200).as(Stream.class);
    }

    public ValueListResponse values(String name) {
        return execute(prepare().get(name + "/values")).status(200).as(ValueListResponse.class);
    }

    public ValueListResponse values(String name, StreamValueParamBuilder... pbs) {
        return execute(prepare().get(name + "/values").params(pbs)).status(200).as(ValueListResponse.class);
    }

    public void addValues(String name, Value... items) {
        List<Value> values = new ArrayList<Value>(items.length);
        Collections.addAll(values, items);

        addValues(name, values);
    }

    public void addValues(String name, Collection<Value> items) {
        Map<String, Object> data = new HashMap<String, Object>(1);
        data.put("values", items);

        execute(prepare().post(name + "/values").body(data)).status(204);
    }

    public void delete(String name) {
        execute(prepare().delete(name)).status(204);
    }

    private static Map<String, Object> wrap(Unit unit) {
        Map<String, Object> data = new HashMap<String, Object>(1);
        data.put("unit", unit);
        return data;
    }

}

