package com.att.m2x.client.internal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;

import com.att.m2x.client.builder.model.UpdateBuilder;
import com.att.m2x.client.builder.param.ParamBuilder;
import com.att.m2x.client.exception.ClientException;


public class RequestBuilderTest {

    private static final String BASE = "BASE_URL";
    private static final String PATH = "PATH";
    private static final String BODY = "{}";

    private ObjectMapper mapper;
    private RequestBuilder builder;


    @Before
    public void setUp() {
        mapper = mock(ObjectMapper.class);
        builder = new RequestBuilder(BASE, mapper);
    }

    @Test
    public void getShouldCreateGetRequest() {
        HttpUriRequest request = builder.get().build();

        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getURI().toString(), is(BASE));
    }

    @Test
    public void getCanHaveAdditionalPath() {
        HttpUriRequest request = builder.get(PATH).build();

        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getURI().toString(), is(BASE + "/" + PATH));
    }

    @Test
    public void postShouldCreatePostRequest() {
        HttpUriRequest request = builder.post().build();

        assertThat(request.getMethod(), is("POST"));
        assertThat(request.getURI().toString(), is(BASE));
    }

    @Test
    public void postCanHaveAdditionalPath() {
        HttpUriRequest request = builder.post(PATH).build();

        assertThat(request.getMethod(), is("POST"));
        assertThat(request.getURI().toString(), is(BASE + "/" + PATH));
    }

    @Test
    public void putShouldCreatePutRequest() {
        HttpUriRequest request = builder.put().build();

        assertThat(request.getMethod(), is("PUT"));
        assertThat(request.getURI().toString(), is(BASE));
    }

    @Test
    public void putCanHaveAdditionalPath() {
        HttpUriRequest request = builder.put(PATH).build();

        assertThat(request.getMethod(), is("PUT"));
        assertThat(request.getURI().toString(), is(BASE + "/" + PATH));
    }

    @Test
    public void deleteShouldCreateDeleteRequest() {
        HttpUriRequest request = builder.delete().build();

        assertThat(request.getMethod(), is("DELETE"));
        assertThat(request.getURI().toString(), is(BASE));
    }

    @Test
    public void deleteCanHaveAdditionalPath() {
        HttpUriRequest request = builder.delete(PATH).build();

        assertThat(request.getMethod(), is("DELETE"));
        assertThat(request.getURI().toString(), is(BASE + "/" + PATH));
    }

    @Test
    public void verifyOneParamCreation() {
        HttpUriRequest request = builder.get().params("param", "value").build();

        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getURI().toString(), is(BASE + "?param=value"));
    }

    @Test
    public void verifyAlternateWayToAddParam() {
        List<String> param = Arrays.asList("param", "value");

        ParamBuilder paramBuilder = mock(ParamBuilder.class);
        when(paramBuilder.apply()).thenReturn(param);

        HttpUriRequest request = builder.get().params(paramBuilder).build();

        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getURI().toString(), is(BASE + "?param=value"));
    }

    @Test
    public void verifySeveralParamCreation() {
        HttpUriRequest request = builder.get().params("paramA", "valueA", "paramB", "valueB").build();

        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getURI().toString(), is(BASE + "?paramA=valueA&paramB=valueB"));
    }

    @Test
    public void verifyAlternateWayToAddSeveralParam() {
        List<String> param = Arrays.asList("paramA", "valueA", "paramB", "valueB");

        ParamBuilder paramBuilder = mock(ParamBuilder.class);
        when(paramBuilder.apply()).thenReturn(param);

        HttpUriRequest request = builder.get().params(paramBuilder).build();

        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getURI().toString(), is(BASE + "?paramA=valueA&paramB=valueB"));
    }

    @Test(expected = ClientException.class)
    public void paramShouldThrowException() {
        builder.get().params("param").build();
    }

    @Test
    public void emptyParamShouldNotChangeUri() {
        List<String> param = Collections.emptyList();

        ParamBuilder paramBuilder = mock(ParamBuilder.class);
        when(paramBuilder.apply()).thenReturn(param);

        HttpUriRequest request = builder.get().params(paramBuilder).build();

        assertThat(request.getMethod(), is("GET"));
        assertThat(request.getURI().toString(), is(BASE));
    }

    @Test
    public void validateBody() throws Exception {
        Map<String, Object> entry = new HashMap<String, Object>();
        when(mapper.writeValueAsString(anyObject())).thenReturn(BODY);

        HttpUriRequest request = builder.post().body(entry).build();

        assertThat(request.getMethod(), is("POST"));

        HttpEntityEnclosingRequestBase requestWithBody = (HttpEntityEnclosingRequestBase) request;
        String body = new Scanner(requestWithBody.getEntity().getContent(), "UTF-8").next();

        assertThat(body, is(BODY));
        verify(mapper).writeValueAsString(entry);
    }

    @Test(expected = ClientException.class)
    public void malformedBodyShouldThrowException() throws Exception {
        Map<String, Object> entry = new HashMap<String, Object>();
        when(mapper.writeValueAsString(anyObject())).thenThrow(JsonProcessingException.class);

        builder.post().body(entry).build();
    }

    @Test
    public void verifyAlternateWayToAddBody() throws Exception {
        Map<String, Object> entry = new HashMap<String, Object>();
        UpdateBuilder updateBuilder = mock(UpdateBuilder.class);
        when(updateBuilder.build()).thenReturn(entry);

        when(mapper.writeValueAsString(anyObject())).thenReturn(BODY);

        HttpUriRequest request = builder.post().body(updateBuilder).build();

        assertThat(request.getMethod(), is("POST"));

        HttpEntityEnclosingRequestBase requestWithBody = (HttpEntityEnclosingRequestBase) request;
        String body = new Scanner(requestWithBody.getEntity().getContent(), "UTF-8").next();

        assertThat(body, is(BODY));
        verify(mapper).writeValueAsString(entry);
    }

}

