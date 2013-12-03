package com.att.m2x.client.api.key;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.att.m2x.client.api.Permission;
import com.att.m2x.client.internal.UpdateBuilder;


public class KeyUpdateBuilder implements UpdateBuilder {

    private Map<String, Object> data = new HashMap<String, Object>();

    private KeyUpdateBuilder() {
        //empty
    }

    @Override
    public Map<String, Object> build() {
        return Collections.unmodifiableMap(data);
    }

    public KeyUpdateBuilder name(String name) {
        data.put("name", name);
        return this;
    }

    public KeyUpdateBuilder permissions(Permission... permissions) {
        Set<Permission> set = new HashSet<Permission>();
        Collections.addAll(set, permissions);
        data.put("permissions", set);
        return this;
    }

    public KeyUpdateBuilder feed(String feed) {
        data.put("feed", feed);
        return this;
    }

    public KeyUpdateBuilder stream(String stream) {
        data.put("stream", stream);
        return this;
    }

    public KeyUpdateBuilder expiresAt(Date expiresAt) {
        data.put("expires_at", expiresAt);
        return this;
    }

    public static KeyUpdateBuilder builder() {
        return new KeyUpdateBuilder();
    }

}

