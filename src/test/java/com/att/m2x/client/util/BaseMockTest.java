package com.att.m2x.client.util;

import org.junit.Before;

import com.att.m2x.client.M2XClient;


public abstract class BaseMockTest extends TestWithSamples {

    protected M2XClient client;

    @Before
    public void setUp() {
        client = new M2XClient("API_KEY", "http://localhost:8080");
    }

}

