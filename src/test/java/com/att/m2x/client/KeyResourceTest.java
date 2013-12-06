package com.att.m2x.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.UUID;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import com.att.m2x.client.api.Permission;
import com.att.m2x.client.api.key.Key;
import com.att.m2x.client.builder.ModelBuilders;
import com.att.m2x.client.exception.ForbiddenException;
import com.att.m2x.client.exception.NotFoundException;
import com.att.m2x.client.exception.UnprocessableEntityException;
import com.att.m2x.client.util.BaseMockTest;


public class KeyResourceTest extends BaseMockTest {

    private static final String ENTITY_ID = UUID.randomUUID().toString().replaceAll("-", "");

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    @Test
    public void loadListOfMastersKey() throws Exception {
        stubFor(get(urlEqualTo("/keys"))
                .willReturn(aResponse().withStatus(200)
                        .withBody(prop("key.list.masterKeys"))));

        List<Key> keys = client.keys().list();

        assertThat(keys, is(not(empty())));
        assertThat(keys.size(), greaterThan(0));
    }

    @Test
    public void loadListOfFeedKeys() throws Exception {
        stubFor(get(urlEqualTo("/keys?feed=" + ENTITY_ID))
                .willReturn(aResponse().withStatus(200)
                        .withBody(prop("key.list.feedKeys"))));

        List<Key> keys = client.keys().list(ENTITY_ID);

        assertThat(keys, is(not(empty())));
        assertThat(keys.size(), greaterThan(0));
    }

    @Test
    public void createNewMasterKey() throws Exception {
        stubFor(post(urlEqualTo("/keys"))
                .withRequestBody(matchingJsonPath("$.name"))
                .withRequestBody(matchingJsonPath("$.permissions"))
                .willReturn(aResponse().withStatus(201)
                        .withBody(prop("key.create.masterKey"))));

        Key response = client.keys().create(ModelBuilders.key().name("Test Master Key").permissions(Permission.GET));

        assertThat(response, is(notNullValue()));
    }

    @Test
    public void getMasterKey() throws Exception {
        stubFor(get(urlEqualTo("/keys/" + ENTITY_ID))
                .willReturn(aResponse().withStatus(200)
                        .withBody(prop("key.get.masterKey"))));

        Key response = client.keys().get(ENTITY_ID);

        assertThat(response, is(notNullValue()));
    }

    @Test(expected = NotFoundException.class)
    public void getWrongMasterKey() throws Exception {
        stubFor(get(urlEqualTo("/keys/" + ENTITY_ID))
                .willReturn(aResponse().withStatus(404)
                        .withBody(prop("key.get.wrongMasterKey"))));

        Key response = client.keys().get(ENTITY_ID);

        assertThat(response, is(notNullValue()));
    }

    @Test
    public void regenerateKeyByKeyId() throws Exception {
        stubFor(post(urlEqualTo("/keys/" + ENTITY_ID + "/regenerate"))
                .willReturn(aResponse().withStatus(201)
                        .withBody(prop("key.regenerate"))));

        Key response = client.keys().regenerate(ENTITY_ID);

        assertThat(response, is(notNullValue()));
    }

    @Test
    public void deleteKeyByKeyId() throws Exception {
        stubFor(delete(urlEqualTo("/keys/" + ENTITY_ID))
                .willReturn(aResponse().withStatus(204)));
        try {
            client.keys().delete(ENTITY_ID);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test(expected = ForbiddenException.class)
    public void deleteKeyByKeyIdForbidden() throws Exception {
        stubFor(delete(urlEqualTo("/keys/" + ENTITY_ID))
                .willReturn(aResponse().withStatus(403)
                        .withBody(prop("key.update.keyException403"))));
        client.keys().delete(ENTITY_ID);
    }

    @Test(expected = UnprocessableEntityException.class)
    public void updateKeyUnprocessableEntityException() throws Exception {
        stubFor(put(urlEqualTo("/keys/" + ENTITY_ID))
                .willReturn(aResponse().withStatus(422)
                        .withBody(prop("key.update.keyException422"))));

        client.keys().update(ENTITY_ID, ModelBuilders.key().name(null));
    }

    @Override
    protected String getPathToProperties() {
        return "mock/key-samples.xml";
    }

}

