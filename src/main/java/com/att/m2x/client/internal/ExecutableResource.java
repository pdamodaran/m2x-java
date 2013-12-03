package com.att.m2x.client.internal;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.exception.ClientException;


public class ExecutableResource extends EmptyResource {

    public ExecutableResource(String path, HttpClient client, ObjectMapper mapper) {
        super(path, client, mapper);
    }

    protected ResponseParser execute(RequestBuilder builder) {
        try {

            HttpResponse response = client.execute(builder.build());

            handleCommonStatusCode(response);

            return new ResponseParser(response, mapper);

        } catch (IOException ioex) {

            throw new ClientException("Problem with connection", ioex);

        }
    }

    private void handleCommonStatusCode(HttpResponse response) {
        //TODO: PK,02/12: add
    }

    protected RequestBuilder prepare() {
        return new RequestBuilder(this.path, this.mapper);
    }

}

