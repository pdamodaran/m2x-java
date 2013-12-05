package com.att.m2x.client.builder;

import com.att.m2x.client.api.DistanceUnit;
import com.att.m2x.client.api.FeedType;


public class QueryBuilders {

    public static PaginationParamBuilder page(int page) {
        return new PaginationParamBuilder().page(page);
    }

    public static PaginationParamBuilder limit(int limit) {
        return new PaginationParamBuilder().limit(limit);
    }

    public static FeedSearchParamBuilder query(String query) {
        return new FeedSearchParamBuilder().query(query);
    }

    public static FeedSearchParamBuilder type(FeedType type) {
        return new FeedSearchParamBuilder().type(type);
    }

    public static FeedSearchParamBuilder latlong(double latitude, double longitude) {
        return new FeedSearchParamBuilder().latlong(latitude, longitude);
    }

    public static FeedSearchParamBuilder within(int distance, DistanceUnit unit) {
        return new FeedSearchParamBuilder().within(distance, unit);
    }

}
