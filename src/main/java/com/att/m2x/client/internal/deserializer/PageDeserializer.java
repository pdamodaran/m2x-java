package com.att.m2x.client.internal.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import com.att.m2x.client.api.Page;
import com.att.m2x.client.api.datasource.Batch;
import com.att.m2x.client.api.datasource.Blueprint;
import com.att.m2x.client.api.datasource.DataSource;
import com.att.m2x.client.api.feed.Feed;


public class PageDeserializer extends JsonDeserializer<Page> {

    private static Map<String, Class> FIELD_TO_CLASS = new HashMap<String, Class>();
    static {
        FIELD_TO_CLASS.put("blueprints", Blueprint.class);
        FIELD_TO_CLASS.put("batches", Batch.class);
        FIELD_TO_CLASS.put("datasources", DataSource.class);
        FIELD_TO_CLASS.put("feeds", Feed.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {

        List items = null;
        int currentPage = 0, limit = 0, pages = 0, total = 0;

        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String name = parser.getCurrentName();
            parser.nextToken();
            if (parser.getCurrentToken() == JsonToken.START_ARRAY
                    && FIELD_TO_CLASS.containsKey(name)) {
                if (parser.nextToken() == JsonToken.START_OBJECT) {

                    items = new ArrayList();
                    Iterator it = parser.readValuesAs(FIELD_TO_CLASS.get(name));
                    while(it.hasNext()) {
                        items.add(it.next());
                    }
                }
            }

            if (parser.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
                if ("current_page".equals(name)) {
                    currentPage = parser.getIntValue();
                } else if ("limit".equals(name)) {
                    limit = parser.getIntValue();
                } else if ("pages".equals(name)) {
                    pages = parser.getIntValue();
                } else if ("total".equals(name)) {
                    total = parser.getIntValue();
                }
            }

        }

        return new Page(items == null ? Collections.emptyList() : items, currentPage, limit, pages, total);
    }

}

