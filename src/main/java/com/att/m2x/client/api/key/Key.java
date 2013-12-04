package com.att.m2x.client.api.key;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.att.m2x.client.api.Permission;


public class Key {

    private String name;
    private String key;
    private boolean master;
    private String feed;
    private String stream;
    @JsonProperty("expires_at") private Date expiresAt;
    private String expired;
    private String origin;
    @JsonProperty("feed_access") private String feedAccess;
    private Set<Permission> permissions;

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public boolean isMaster() {
        return master;
    }

    public String getFeed() {
        return feed;
    }

    public String getStream() {
        return stream;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public String getExpired() {
        return expired;
    }

    public String getOrigin() {
        return origin;
    }

    public String getFeedAccess() {
        return feedAccess;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

}

