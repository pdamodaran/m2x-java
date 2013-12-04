package com.att.m2x.client.resource.feed;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.stream.*;
import com.att.m2x.client.internal.ExecutableResource;
import com.att.m2x.client.internal.RequestBuilder;


public class StreamSubResource extends ExecutableResource {

    public StreamSubResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    public StreamListResponse list() {
        return execute(prepare().get()).status(200).as(StreamListResponse.class);
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

    //TODO: PK,04/12: replace with builder?
    public ValueListResponse values(String name, Date start, Date end, Integer limit) {
        RequestBuilder request = prepare().get(name + "/values");
        if (start != null) {
            request.params("start", toStr(start));
        }
        if (end != null) {
            request.params("start", toStr(end));
        }
        if (limit != null) {
            request.params("limit", limit.toString());
        }

        return execute(request).status(200).as(ValueListResponse.class);
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

    private static String toStr(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSZZ");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df.format(date);
    }

}

