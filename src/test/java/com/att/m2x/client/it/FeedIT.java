package com.att.m2x.client.it;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.att.m2x.client.api.DistanceUnit;
import com.att.m2x.client.api.Page;
import com.att.m2x.client.api.feed.BatchFeed;
import com.att.m2x.client.api.feed.BlueprintFeed;
import com.att.m2x.client.api.feed.DataSourceFeed;
import com.att.m2x.client.api.feed.Feed;
import com.att.m2x.client.api.feed.Location;
import com.att.m2x.client.api.feed.LogEntry;
import com.att.m2x.client.builder.QueryBuilders;
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
    public void verifyLocationSearch() {
        Page<Feed> feeds = client.feeds().list(
                QueryBuilders.latlong(-37.978, -57.547).within(100, DistanceUnit.KM)
        );

        assertThat(feeds, is(notNullValue()));
        assertThat(feeds.getData().size(), greaterThan(0));
    }

    @Test
    public void verifyPagination() {
        Page<Feed> feeds = client.feeds().list(QueryBuilders.page(1).limit(1));

        assertThat(feeds, is(notNullValue()));
        assertThat(feeds.getData().size(), greaterThan(0));
        assertThat(feeds.getLimit(), is(1));
        assertThat(feeds.getCurrentPage(), is(1));
        assertThat(feeds.getTotal(), greaterThan(0));
    }

    @Test
    public void verifyLocationSearchAndPagination() {
        Page<Feed> feeds = client.feeds().list(
                QueryBuilders.latlong(-37.978, -57.547).within(100, DistanceUnit.KM),
                QueryBuilders.page(1).limit(1)
        );

        assertThat(feeds, is(notNullValue()));
        assertThat(feeds.getData().size(), greaterThan(0));
        assertThat(feeds.getLimit(), is(1));
        assertThat(feeds.getCurrentPage(), is(1));
        assertThat(feeds.getTotal(), greaterThan(0));
    }

    @Test
    public void verifyLocationInFeed() {
        Feed feed = client.feeds().get(prop("feed.with-location.id"));

        assertThat(feed.getLocation(), is(notNullValue()));

        Location location = client.feeds().feed(prop("feed.with-location.id")).location();

        assertThat(location, is(notNullValue()));
        assertThat(feed.getLocation().getLatitude(), is(location.getLatitude()));
        assertThat(feed.getLocation().getLongitude(), is(location.getLongitude()));
        assertThat(feed.getLocation().getName(), is(location.getName()));
    }

    @Test
    public void logShouldBeFilledWithEvents() {
        List<LogEntry> entries = client.feeds().feed(prop("feed.batch.id")).log();

        assertThat(entries, is(not(empty())));
    }

}

