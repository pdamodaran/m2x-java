package com.att.m2x.client.builder.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.att.m2x.client.api.Permission;


public class KeyBuilder extends UpdateBuilder {

    public KeyBuilder name(String name) {
        data.put("name", name);
        return this;
    }

    public KeyBuilder permissions(Permission... permissions) {
        Set<Permission> set = new HashSet<Permission>();
        Collections.addAll(set, permissions);
        data.put("permissions", set);
        return this;
    }

    public KeyBuilder feed(String feed) {
        data.put("feed", feed);
        return this;
    }

    public KeyBuilder stream(String stream) {
        data.put("stream", stream);
        return this;
    }

    public KeyBuilder expiresAt(Date expiresAt) {
        data.put("expires_at", expiresAt);
        return this;
    }

}

