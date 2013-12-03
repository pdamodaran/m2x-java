package com.att.m2x.client.resource.feed;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.stream.Stream;
import com.att.m2x.client.api.stream.StreamListRespond;
import com.att.m2x.client.api.stream.ValueListRespond;
import com.att.m2x.client.internal.ExecutableResource;


public class StreamSubResource extends ExecutableResource {

    public StreamSubResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    public StreamListRespond list() {
        return execute(prepare().get()).status(200).as(StreamListRespond.class);
    }

    public Stream view(String name) {
        return execute(prepare().get(name)).status(200).as(Stream.class);
    }

    public ValueListRespond values(String name) {
        return execute(prepare().get(name + "/values")).status(200).as(ValueListRespond.class);
    }

    public void delete(String name) {
        execute(prepare().delete(name)).status(204);
    }

}

