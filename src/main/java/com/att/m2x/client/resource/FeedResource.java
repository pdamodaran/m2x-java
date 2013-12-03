package com.att.m2x.client.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.api.feed.CommonFeed;
import com.att.m2x.client.api.feed.Location;
import com.att.m2x.client.internal.ExecutableResource;
import com.att.m2x.client.resource.feed.StreamSubResource;
import com.att.m2x.client.resource.feed.TriggerSubResource;


public class FeedResource extends ExecutableResource {

    public FeedResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    //TODO: PK,03/12: list

    public CommonFeed get(String id) {
        return execute(prepare().get(id)).status(200).as(CommonFeed.class);
    }

    public Location location(String id) {
        return execute(prepare().get(id + "/location")).status(200).as(Location.class);
    }

    public FeedResourceDirector feed(String id) {
        return drill(id, FeedResourceDirector.class);
    }

    public static class FeedResourceDirector extends ExecutableResource {

        public FeedResourceDirector(String path, HttpClient client, ObjectMapper mapper) {
            super(path, client, mapper);
        }

        public StreamSubResource streams() {
            return drill("streams", StreamSubResource.class);
        }

        public TriggerSubResource triggers() {
            return drill("triggers", TriggerSubResource.class);
        }

    }

}

