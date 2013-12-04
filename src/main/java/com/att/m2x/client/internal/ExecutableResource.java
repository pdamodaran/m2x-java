package com.att.m2x.client.internal;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import com.att.m2x.client.exception.ClientException;
import com.att.m2x.client.exception.ForbiddenException;
import com.att.m2x.client.exception.NotFoundException;
import com.att.m2x.client.exception.UnprocessableEntityException;


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

            throw new ClientException("Connection or protocol error", ioex);

        }
    }

    private void handleCommonStatusCode(HttpResponse response) throws IOException{

        if (response.getStatusLine().getStatusCode() == 404) {
            throw new NotFoundException(EntityUtils.toString(response.getEntity()));
        }

        if (response.getStatusLine().getStatusCode() == 403) {
            throw new ForbiddenException(EntityUtils.toString(response.getEntity()));
        }

        if (response.getStatusLine().getStatusCode() == 422) {
            throw new UnprocessableEntityException(EntityUtils.toString(response.getEntity()));
        }

    }

    protected RequestBuilder prepare() {
        return new RequestBuilder(this.path, this.mapper);
    }

}

