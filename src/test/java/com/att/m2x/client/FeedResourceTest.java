package com.att.m2x.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import com.att.m2x.client.api.Page;
import com.att.m2x.client.api.feed.Feed;
import com.att.m2x.client.api.feed.Location;
import com.att.m2x.client.util.BaseMockTest;


public class FeedResourceTest extends BaseMockTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Override
    protected String getPathToProperties() {
        return "mock/feed-samples.xml";
    }

    @Test
    public void loadListOfFeeds() {
        stubFor(get(urlEqualTo("/feeds"))
                .willReturn(aResponse().withStatus(200)
                        .withBody(prop("key.list.feeds"))));

        Page<Feed> feeds = client.feeds().list();

        assertThat(feeds, is(notNullValue()));
        assertThat(feeds.getData().size(), greaterThan(0));
    }

    @Test
    public void loadFeedById() {
        stubFor(get(urlEqualTo("/feeds/FEED_ID"))
                .willReturn(aResponse().withStatus(200)
                        .withBody(prop("key.get.byId"))));

        Feed feed = client.feeds().get("FEED_ID");

        assertThat(feed, is(notNullValue()));
        assertEquals("KEY_ID", feed.getKey());
    }

    @Test
    public void loadFeedLocation() {
        stubFor(get(urlEqualTo("/feeds/FEED_ID"))
                .willReturn(aResponse().withStatus(200)
                        .withBody(prop("key.get.byId"))));

        Location location = client.feeds().get("FEED_ID").getLocation();

        assertEquals("Storage Room", location.getName());
    }
}
