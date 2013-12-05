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

import com.att.m2x.client.api.feed.LogEntry;
import com.att.m2x.client.api.key.Key;
import com.att.m2x.client.api.stream.Stream;
import com.att.m2x.client.api.trigger.Trigger;


public class BasketDeserializer extends JsonDeserializer<Basket> {

    private static Map<String, Class> FIELD_TO_CLASS = new HashMap<String, Class>();
    static {
        FIELD_TO_CLASS.put("keys", Key.class);
        FIELD_TO_CLASS.put("streams", Stream.class);
        FIELD_TO_CLASS.put("triggers", Trigger.class);
        FIELD_TO_CLASS.put("requests", LogEntry.class);
    }


    @Override
    @SuppressWarnings("unchecked")
    public Basket deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {

        List items;

        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String name = parser.getCurrentName();
            if (parser.nextToken() == JsonToken.START_ARRAY
                    && FIELD_TO_CLASS.containsKey(name)) {
                if (parser.nextToken() == JsonToken.START_OBJECT) {

                    items = new ArrayList();
                    Iterator it = parser.readValuesAs(FIELD_TO_CLASS.get(name));
                    while(it.hasNext()) {
                        items.add(it.next());
                    }

                    return new Basket(items);
                }
            }
        }

        return new Basket(Collections.emptyList());
    }

}

