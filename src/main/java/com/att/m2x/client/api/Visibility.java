package com.att.m2x.client.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


@JsonSerialize(using=ToStringSerializer.class)
public enum Visibility {

    PRIVATE, PUBLIC;

    @JsonValue
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    @JsonCreator
    public static Visibility toEnum(String str) {
        return Visibility.valueOf(str.toUpperCase());
    }

}

