package com.att.m2x.client.it;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.att.m2x.client.M2XClient;
import com.att.m2x.client.api.stream.Stream;
import com.att.m2x.client.api.stream.Unit;
import com.att.m2x.client.api.stream.Value;
import com.att.m2x.client.api.stream.ValueListResponse;
import com.att.m2x.client.builder.QueryBuilders;
import com.att.m2x.client.util.BaseResourceIT;


public class StreamIT extends BaseResourceIT {

    @Test
    public void shouldGetListOfStream() {
        List<Stream> streams = client.feeds().feed(prop("feed.id")).streams().list();

        assertThat(streams, is(not(empty())));
    }

    @Test
    public void createNewStream() {
        Stream stream = client.feeds().feed(prop("feed.id")).streams().create(name, new Unit("speed", "kph"));

        assertThat(stream, is(notNullValue()));
        assertThat(stream.getName(), is(name));
    }

    @Test
    public void streamShouldHaveValues() {
        ValueListResponse response = client.feeds().feed(prop("feed.id")).streams()
                .values(prop("stream.id"));

        assertThat(response, is(notNullValue()));
    }

    @Test
    public void streamValueAcceptLimitAndDate() {
        ValueListResponse response = client.feeds().feed(prop("feed.id")).streams()
                .values(prop("stream.id"), QueryBuilders.start(new Date()).limit(1));

        assertThat(response, is(notNullValue()));
        assertThat(response.getLimit(), is(1));
        assertThat(response.getValues(), is(empty()));
    }

    @Test
    public void addValueToStream() {
        client.feed(prop("feed.id")).streams().addValues(prop("stream.id"), new Value("1000"), new Value("1000"));
    }

}

