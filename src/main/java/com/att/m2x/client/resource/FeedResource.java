package com.att.m2x.client.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.feed.CommonFeed;
import com.att.m2x.client.api.feed.FeedListResponse;
import com.att.m2x.client.internal.resource.ExecutableResource;
import com.att.m2x.client.resource.feed.FeedSubResource;


public class FeedResource extends ExecutableResource {

    public FeedResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    public FeedListResponse list() {
        return execute(prepare().get()).status(200).as(FeedListResponse.class);
    }

    public FeedListResponse list(String query) {
        return execute(prepare().get().params("q", query)).status(200).as(FeedListResponse.class);
    }

    public FeedListResponse listByType(String query) {
        return execute(prepare().get().params("type", query)).status(200).as(FeedListResponse.class);
    }

    //TODO: PK,04/12, replace type with enum
    public FeedListResponse list(String query, String type) {
        return execute(prepare().get().params("q", query, "type", type)).status(200).as(FeedListResponse.class);
    }

    public CommonFeed get(String id) {
        return execute(prepare().get(id)).status(200).as(CommonFeed.class);
    }

    public FeedSubResource feed(String id) {
        return drill(id, FeedSubResource.class);
    }

}

