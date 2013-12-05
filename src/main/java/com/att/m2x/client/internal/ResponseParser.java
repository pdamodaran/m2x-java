package com.att.m2x.client.internal;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.http.HttpResponse;

import com.att.m2x.client.api.Page;
import com.att.m2x.client.internal.deserializer.Basket;
import com.att.m2x.client.exception.ClientException;


public class ResponseParser {

    private HttpResponse response;
    private ObjectMapper mapper;

    public ResponseParser(HttpResponse response, ObjectMapper mapper) {
        this.response = response;
        this.mapper = mapper;
    }

    public ResponseParser status(int status) {
        if (response.getStatusLine().getStatusCode() != status) {
            throw new ClientException("Wrong status, expected:" + status + ", was:" + response.getStatusLine().getStatusCode());
        }
        return this;
    }

    public <T> T as(Class<T> returnType) {
        ObjectReader reader = mapper.reader(returnType);
        try {

            return reader.readValue(response.getEntity().getContent());

        } catch (JsonProcessingException jpex) {
            throw new ClientException("Malformed response", jpex);
        } catch (IOException ioex) {
            throw new ClientException("Response cant be processed", ioex);
        } catch (IllegalStateException isex) {
            throw new ClientException("Response cant be processed", isex);
        }

    }

    public <T> Page<T> page(Class<T> resultType) {
        JavaType type = mapper.getTypeFactory().constructParametricType(Page.class, resultType);

        ObjectReader reader = mapper.reader(type);
        try {

            return reader.readValue(response.getEntity().getContent());

        } catch (JsonProcessingException jpex) {
            throw new ClientException("Malformed response", jpex);
        } catch (IOException ioex) {
            throw new ClientException("Response cant be processed", ioex);
        } catch (IllegalStateException isex) {
            throw new ClientException("Response cant be processed", isex);
        }

    }

    public <T> List<T> list(Class<T> resultType) {
        JavaType type = mapper.getTypeFactory().constructParametricType(Basket.class, resultType);

        ObjectReader reader = mapper.reader(type);
        try {

            Basket<T> basket =  reader.readValue(response.getEntity().getContent());
            return basket.getData();

        } catch (JsonProcessingException jpex) {
            throw new ClientException("Malformed response", jpex);
        } catch (IOException ioex) {
            throw new ClientException("Response cant be processed", ioex);
        } catch (IllegalStateException isex) {
            throw new ClientException("Response cant be processed", isex);
        }

    }

}

