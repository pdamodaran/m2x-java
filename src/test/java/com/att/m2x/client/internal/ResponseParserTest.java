package com.att.m2x.client.internal;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.junit.Before;
import org.junit.Test;

import com.att.m2x.client.exception.ClientException;


public class ResponseParserTest {

    private ObjectMapper mapper;
    private HttpResponse response;
    private ResponseParser parser;

    @Before
    public void setUp() {
        mapper = mock(ObjectMapper.class);
        response = mock(HttpResponse.class);

        parser = new ResponseParser(response, mapper);
    }

    @Test
    public void statusVerificationShouldPass() {
        StatusLine line = mock(StatusLine.class);

        when(response.getStatusLine()).thenReturn(line);
        when(line.getStatusCode()).thenReturn(200);

        parser.status(200);
    }

    @Test(expected = ClientException.class)
    public void statusRaiseExceptionIfStatusDoNotMatch() {
        StatusLine line = mock(StatusLine.class);

        when(response.getStatusLine()).thenReturn(line);
        when(line.getStatusCode()).thenReturn(500);

        parser.status(200);
    }



}
