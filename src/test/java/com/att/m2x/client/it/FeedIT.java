package com.att.m2x.client.it;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.att.m2x.client.api.feed.BatchFeed;
import com.att.m2x.client.api.feed.BlueprintFeed;
import com.att.m2x.client.api.feed.CommonFeed;
import com.att.m2x.client.api.feed.DataSourceFeed;
import com.att.m2x.client.api.feed.Location;
import com.att.m2x.client.util.BaseResourceIT;


public class FeedIT extends BaseResourceIT {

    @Test
    public void verifyBlueprintFeed() {
        BlueprintFeed feed = (BlueprintFeed) client.feeds().get(prop("feed.blueprint.id"));

        assertThat(feed, is(notNullValue()));
    }

    @Test
    public void verifyBatchFeed() {
        BatchFeed feed = (BatchFeed) client.feeds().get(prop("feed.batch.id"));

        assertThat(feed, is(notNullValue()));
        //~
        assertThat(feed.getDataSources(), is(notNullValue()));
        assertThat(feed.getDataSources().getTotal(), greaterThan(0));
    }

    @Test
    public void verifyDataSourceFeed() {
        DataSourceFeed feed = (DataSourceFeed) client.feeds().get(prop("feed.datasource.id"));

        assertThat(feed, is(notNullValue()));
        //~
        assertThat(feed.getBatch(), is(notNullValue()));
    }

    @Test
    public void verifyLocationInFeed() {
        CommonFeed feed = client.feeds().get(prop("feed.with-location.id"));

        assertThat(feed.getLocation(), is(notNullValue()));

        Location location = client.feeds().feed(prop("feed.with-location.id")).location();

        assertThat(location, is(notNullValue()));
        assertThat(feed.getLocation().getLatitude(), is(location.getLatitude()));
        assertThat(feed.getLocation().getLongitude(), is(location.getLongitude()));
        assertThat(feed.getLocation().getName(), is(location.getName()));
    }



}
