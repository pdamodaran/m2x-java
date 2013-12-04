package com.att.m2x.client.it;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.att.m2x.client.api.feed.BatchFeed;
import com.att.m2x.client.api.feed.BlueprintFeed;
import com.att.m2x.client.api.feed.DataSourceFeed;


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


}
