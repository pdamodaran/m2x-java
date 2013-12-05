package com.att.m2x.client.builder;

import com.att.m2x.client.api.DistanceUnit;
import com.att.m2x.client.api.FeedType;


public class FeedSearchParamBuilder extends ParamBuilder {

    public FeedSearchParamBuilder query(String query) {
        entries.put("q", query);
        return this;
    }

    public FeedSearchParamBuilder type(FeedType type) {
        entries.put("type", type.toString().toLowerCase());
        return this;
    }

    public FeedSearchParamBuilder latlong(double latitude, double longitude) {
        entries.put("latitude", String.valueOf(latitude));
        entries.put("longitude", String.valueOf(longitude));
        return this;
    }

    public FeedSearchParamBuilder within(int distance, DistanceUnit unit) {
        entries.put("distance", String.valueOf(distance));
        entries.put("distance_unit", unit.toString().toLowerCase());
        return this;
    }

}
