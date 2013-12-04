package com.att.m2x.client.api.feed;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.att.m2x.client.api.Status;
import com.att.m2x.client.api.Visibility;
import com.att.m2x.client.api.stream.Stream;


@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @Type(value = BatchFeed.class, name="batch"),
        @Type(value = BlueprintFeed.class, name="blueprint"),
        @Type(value = DataSourceFeed.class, name="datasource")
})
public abstract class CommonFeed {

    protected String id;
    protected String name;
    protected String description;
    protected Visibility visibility;
    protected Status status;
    protected String feed;
    protected List<String> tags;
    protected String url;
    protected String key;
    protected Date created;
    protected Date updated;
    protected Location location;
    protected List<Stream> streams;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public Status getStatus() {
        return status;
    }

    public String getFeed() {
        return feed;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public Location getLocation() {
        return location;
    }

    public List<Stream> getStreams() {
        return streams;
    }

}

