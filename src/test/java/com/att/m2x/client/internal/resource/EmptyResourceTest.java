package com.att.m2x.client.internal.resource;

import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class EmptyResourceTest {


    @Test
    public void verifyDrill() {
        String path = "PATH";
        HttpClient client = mock(HttpClient.class);
        ObjectMapper mapper = mock(ObjectMapper.class);

        EmptyResource baseResource = new EmptyResource(path, client, mapper);
        TestResource childResource = baseResource.drill("SUB", TestResource.class);

        assertThat(childResource.getPath(), is("PATH/SUB"));
        assertThat(childResource.getClient(), is(client));
        assertThat(childResource.getMapper(), is(mapper));

    }

    public static class TestResource extends EmptyResource {

        public TestResource(String path, HttpClient client, ObjectMapper mapper) {
            super(path, client, mapper);
        }

        public String getPath() {
            return path;
        }

        public HttpClient getClient() {
            return client;
        }

        public ObjectMapper getMapper() {
            return mapper;
        }

    }

}
