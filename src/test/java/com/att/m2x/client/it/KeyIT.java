package com.att.m2x.client.it;

import static com.att.m2x.client.api.key.KeyUpdateBuilder.builder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.att.m2x.client.M2XClient;
import com.att.m2x.client.api.Permission;
import com.att.m2x.client.api.key.Key;
import com.att.m2x.client.api.key.KeyListResponse;
import com.att.m2x.client.exception.NotFoundException;
import com.att.m2x.client.util.TestWithSamples;


public class KeyIT extends TestWithSamples {

    private M2XClient client;
    private String name;

    @Before
    public void setUp() {
        client = new M2XClient(prop("key.master"));
        name = "it-test-" + UUID.randomUUID();
    }

    @Test
    public void loadShouldReturnAtLeastOneKey() {
        KeyListResponse response = client.keys().list();

        assertThat(response.getKeys().size(), is(greaterThan(0)));
    }

    @Test
    public void validateKeyCreation() {
        Key key = client.keys().create(
                builder().name(name).permissions(Permission.GET, Permission.POST)
        );

        assertThat(key, is(notNullValue()));
        assertThat(key.getName(), is(name));
        assertThat(key.getPermissions(), hasItems(Permission.GET, Permission.POST));
    }

    @Test
    public void validateKeyCreationWithExpirationDate() {
        Date expiration = (new Date(new Date().getTime() + 60 * 1000));

        Key key = client.keys().create(
                builder().name(name).permissions(Permission.GET)
                .expiresAt(expiration)
        );

        assertThat(key, notNullValue());
    }

    @Test
    public void regenerateShouldChangeKeyIdentity() {
        Key key = client.keys().create(
                builder().name(name).permissions(Permission.GET, Permission.POST)
        );
        assertThat(key, is(notNullValue()));

        Key regenerated = client.keys().regenerate(key.getKey());

        assertThat(regenerated, is(notNullValue()));
        assertThat(regenerated.getName(), is(key.getName()));
        assertThat(regenerated.getKey(), is(not(key.getKey())));
    }

    //TODO: PK,04/12: @Test(expected = NotFoundException.class)
    @Test
    public void validateDelete() {
        Key key = client.keys().create(
                builder().name(name).permissions(Permission.GET, Permission.POST)
        );
        assertThat(key, is(notNullValue()));

        try {
            client.keys().delete(key.getKey());
        } catch (Exception ex) {
            fail();
        }

        //client.keys().get(key.getKey());
    }

    //~

    @Override
    protected String getPathToProperties() {
        String override = System.getenv("it.keys");
        return override == null || override.isEmpty() ? "it/it-keys.xml" : override;
    }

}

