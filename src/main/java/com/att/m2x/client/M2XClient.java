package com.att.m2x.client;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import com.att.m2x.client.internal.resource.EmptyResource;
import com.att.m2x.client.resource.BatchResource;
import com.att.m2x.client.resource.BlueprintResource;
import com.att.m2x.client.resource.DataSourceResource;
import com.att.m2x.client.resource.FeedResource;
import com.att.m2x.client.resource.KeyResource;
import com.att.m2x.client.resource.feed.FeedSubResource;


public class M2XClient {

    private EmptyResource root;


    public M2XClient(String key) {
        //TODO: PK,02/12: always remove last slash
        this(key, "http://api-m2x.att.com/v1");
    }

    public M2XClient(String apiKey, String apiEndpoint) {
        assert apiKey != null;
        assert apiEndpoint != null;

        RequestConfig config = RequestConfig.custom()
                //TODO: PK,29/11:builder
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .build();

        Header userAgentHeader = new BasicHeader("User-Agent", "M2X/Java client 0.1");
        Header apiKeyHeader = new BasicHeader("X-M2X-KEY", apiKey);

        HttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .setDefaultHeaders(Arrays.asList(userAgentHeader, apiKeyHeader))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //TODO: PK,04/12: add PropertyNamingStrategy to translate camelCase to '_'

        root = new EmptyResource(apiEndpoint, client, mapper);
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

    public FeedSubResource feed(String id) {
        return root.drill("feeds/" + id, FeedSubResource.class);
    }

}

