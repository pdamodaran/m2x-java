package com.att.m2x.client;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import com.att.m2x.client.internal.EmptyResource;
import com.att.m2x.client.resource.BatchResource;
import com.att.m2x.client.resource.BlueprintResource;
import com.att.m2x.client.resource.DataSourceResource;
import com.att.m2x.client.resource.FeedResource;
import com.att.m2x.client.resource.KeyResource;


public class M2XClient {

    private EmptyResource root;


    public M2XClient(String key) {
        //TODO: PK,02/12: always remove last slash
        this(key, "http://api-m2x.att.com/v1");
    }

    public M2XClient(String key, String url) {
        assert key != null;
        assert url != null;

        RequestConfig config = RequestConfig.custom()
                //TODO: PK,29/11:builder
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .build();

        Header userAgent = new BasicHeader("User-Agent", "M2X/Java client 0.1");
        Header apiKey = new BasicHeader("X-M2X-KEY", key);

        HttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .setDefaultHeaders(Arrays.asList(userAgent, apiKey))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //TODO: PK,04/12: add PropertyNamingStrategy to translate camelCase to '_'

        root = new EmptyResource(url, client, mapper);
    }


    public KeyResource keys() {
        return root.drill("keys", KeyResource.class);
    }

    public BlueprintResource blueprints() {
        return root.drill("blueprints", BlueprintResource.class);
    }

    public BatchResource batches() {
        return root.drill("batches", BatchResource.class);
    }

    public DataSourceResource dataSources() {
        return root.drill("datasources", DataSourceResource.class);
    }

    public FeedResource feeds() {
        return root.drill("feeds", FeedResource.class);
    }

}

