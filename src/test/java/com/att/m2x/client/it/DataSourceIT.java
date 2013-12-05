package com.att.m2x.client.it;

import static com.att.m2x.client.api.datasource.DataSourceUpdateBuilder.builder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.att.m2x.client.api.Page;
import com.att.m2x.client.api.Status;
import com.att.m2x.client.api.Visibility;
import com.att.m2x.client.api.datasource.Batch;
import com.att.m2x.client.api.datasource.Blueprint;
import com.att.m2x.client.api.datasource.DataSource;
import com.att.m2x.client.builder.QueryBuilders;
import com.att.m2x.client.util.BaseResourceIT;


public class DataSourceIT extends BaseResourceIT {

    @Test
    public void verifyBlueprint() {
        Blueprint blueprint = client.blueprints().get(prop("blueprint.id"));

        assertThat(blueprint, is(notNullValue()));
        assertThat(blueprint.getVisibility(), is(Visibility.PRIVATE));
        assertThat(blueprint.getStatus(), is(Status.ENABLED));
        assertThat(blueprint.getCreated(), is(notNullValue()));
        assertThat(blueprint.getUpdated(), is(notNullValue()));
    }

    @Test
    public void verifyBatch() {
        Batch batch = client.batches().get(prop("batch.id"));

        assertThat(batch, is(notNullValue()));
        assertThat(batch.getVisibility(), is(Visibility.PRIVATE));
        assertThat(batch.getStatus(), is(Status.ENABLED));
        assertThat(batch.getCreated(), is(notNullValue()));
        assertThat(batch.getUpdated(), is(notNullValue()));
        //~
        assertThat(batch.getDataSources(), is(notNullValue()));
        assertThat(batch.getDataSources().getTotal(), greaterThan(0));
    }

    @Test
    public void verifyDataSource() {
        DataSource dataSource = client.dataSources().get(prop("datasource.id"));

        assertThat(dataSource, is(notNullValue()));
        assertThat(dataSource.getVisibility(), is(Visibility.PRIVATE));
        assertThat(dataSource.getStatus(), is(Status.ENABLED));
        assertThat(dataSource.getCreated(), is(notNullValue()));
        assertThat(dataSource.getUpdated(), is(notNullValue()));
        //~
        assertThat(dataSource.getBatch(), is(notNullValue()));
    }

    @Test
    public void createBlueprintWithTags() {
        Blueprint blueprint = client.blueprints().create(
                builder().name(name).visibility(Visibility.PRIVATE)
                        .tags("TagA", "TagB")
        );

        assertThat(blueprint, is(notNullValue()));
        assertThat(blueprint.getName(), is(name));
        assertThat(blueprint.getVisibility(), is(Visibility.PRIVATE));
        assertThat(blueprint.getTags(), hasItems("TagA", "TagB"));
    }

    @Test
    public void verifyPagination() {
        Page<Blueprint> blueprints = client.blueprints().list(QueryBuilders.page(1).limit(1));

        assertThat(blueprints, is(notNullValue()));
        assertThat(blueprints.getData().size(), greaterThan(0));
        assertThat(blueprints.getLimit(), is(1));
        assertThat(blueprints.getCurrentPage(), is(1));
        assertThat(blueprints.getTotal(), greaterThan(0));
    }

    @Test
    public void canViewDataSourcesInBatch() {
        Page<DataSource> dataSources = client.batches().batch(prop("batch.id")).dataSources();

        assertThat(dataSources, is(notNullValue()));
        assertThat(dataSources.getData().size(), greaterThan(0));
        assertThat(dataSources.getData().get(0).getBatch(), is(prop("batch.id")));
    }

}

