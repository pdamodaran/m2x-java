package com.att.m2x.client.builder.param;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class StreamValueParamBuilder extends ParamBuilder {

    public StreamValueParamBuilder start(Date date) {
        entries.put("start", convert(date));
        return this;
    }

    public StreamValueParamBuilder end(Date date) {
        entries.put("end", convert(date));
        return this;
    }

    public StreamValueParamBuilder limit(int limit) {
        entries.put("limit", String.valueOf(limit));
        return this;
    }

    private static String convert(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSX");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        return df.format(date);
    }

}

