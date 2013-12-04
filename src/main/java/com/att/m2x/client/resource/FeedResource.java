package com.att.m2x.client.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.feed.CommonFeed;
import com.att.m2x.client.internal.ExecutableResource;
import com.att.m2x.client.resource.feed.FeedSubResource;


public class FeedResource extends ExecutableResource {

    public FeedResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    //TODO: PK,03/12: list

    public CommonFeed get(String id) {
        return execute(prepare().get(id)).status(200).as(CommonFeed.class);
    }

    public FeedSubResource feed(String id) {
        return drill(id, FeedSubResource.class);
    }

}

