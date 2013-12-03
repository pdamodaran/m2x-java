package com.att.m2x.client.api.trigger;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


@JsonSerialize(using=ToStringSerializer.class)
public enum Condition {

    LESS("<"), LESS_OR_EQUAL("<="), EQUAL("="), GREAT_OR_EQUAL(">="), GREAT("<");

    private String sign;

    private Condition(String sign) {
        this.sign = sign;
    }

    @JsonValue
    @Override
    public String toString() {
        return sign;
    }

    private static Map<String, Condition> values = new HashMap<String, Condition>();
    static {
        for (Condition condition : Condition.values()) {
            values.put(condition.sign, condition);
        }
    }

    @JsonCreator
    public static Condition toEnum(String sign) {
        return values.get(sign);
    }

}

