package com.att.m2x.client.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.Page;
import com.att.m2x.client.api.feed.Feed;
import com.att.m2x.client.builder.ParamBuilder;
import com.att.m2x.client.internal.resource.ExecutableResource;
import com.att.m2x.client.resource.feed.FeedSubResource;


public class FeedResource extends ExecutableResource {

    public FeedResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    public Page<Feed> list() {
        return execute(prepare().get()).status(200).page(Feed.class);
    }

    public Page<Feed> list(ParamBuilder... pbs) {
        return execute(prepare().get().params(pbs)).status(200).page(Feed.class);
    }

    public Feed get(String id) {
        return execute(prepare().get(id)).status(200).as(Feed.class);
    }

    public FeedSubResource feed(String id) {
        return drill(id, FeedSubResource.class);
    }

}

