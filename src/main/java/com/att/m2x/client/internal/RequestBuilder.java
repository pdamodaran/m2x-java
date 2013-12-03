package com.att.m2x.client.internal;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Consts;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import com.att.m2x.client.exception.ClientException;


public class RequestBuilder {

    private enum HttpMethod {
        GET, POST, PUT, DELETE
    }

    private final ObjectMapper mapper;

    private HttpMethod method;
    private String baseUrl;
    private String subUrl = "";
    private String param = "";
    private String body = "";


    public RequestBuilder(String baseUrl, ObjectMapper mapper) {
        this.mapper = mapper;
        this.baseUrl = baseUrl;
    }


    public RequestBuilder get() {
        this.method = HttpMethod.GET;
        return this;
    }

    public RequestBuilder get(String path) {
        this.method = HttpMethod.GET;
        this.subUrl = path;
        return this;
    }

    public RequestBuilder post() {
        this.method = HttpMethod.POST;
        return this;
    }

    public RequestBuilder post(String path) {
        this.method = HttpMethod.POST;
        this.subUrl = path;
        return this;
    }

    public RequestBuilder put() {
        this.method = HttpMethod.PUT;
        return this;
    }

    public RequestBuilder put(String path) {
        this.method = HttpMethod.PUT;
        this.subUrl = path;
        return this;
    }

    public RequestBuilder delete(String path) {
        this.method = HttpMethod.DELETE;
        this.subUrl = path;
        return this;
    }

    public RequestBuilder body(UpdateBuilder builder) {
        return body(builder.build());
    }

    public RequestBuilder body(Map<String, Object> data) {
        try {
            this.body = mapper.writeValueAsString(data)
                    //TODO: PK,03/12: Fix jackson issues with timezone
                    .replaceAll("(T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})([-+]+\\d{4})", "$1Z");
        } catch (JsonProcessingException jpex) {
            throw new ClientException("Malformed builder");
        }
        return this;
    }

    public RequestBuilder params(String... params) {
        Iterator<String> it = Arrays.asList(params).iterator();

        StringBuffer buffer = new StringBuffer();
        while (it.hasNext()) {
            String name = it.next();
            if (!it.hasNext()) {
                throw new ClientException("Malformed params list");
            }
            if (buffer.length() > 0) {
                buffer.append("&");
            }
            buffer.append(name + "=" + it.next());
        }
        this.param = buffer.toString();

        return this;
    }


    public HttpUriRequest build() {
        StringBuilder buffer = new StringBuilder(baseUrl);
        if (!subUrl.isEmpty()) {
            buffer.append("/").append(subUrl);
        }
        if (!param.isEmpty()) {
            buffer.append("?").append(param);
        }
        String url = buffer.toString();


        HttpUriRequest request = null;

        switch (method) {
            case GET:
                request = new HttpGet(url);
                break;
            case POST:
                request = withBody(new HttpPost(url));
                break;
            case PUT:
                request = withBody(new HttpPut(url));
                break;
            case DELETE:
                request = new HttpDelete(url);
                break;
        }

        return request;
    }

    private HttpUriRequest withBody(HttpEntityEnclosingRequestBase request) {
        if (!body.isEmpty()) {
            request.setEntity(new StringEntity(body, ContentType.create("application/json", Consts.UTF_8)));
        }
        return request;
    }

}

