package com.att.m2x.client.internal.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import com.att.m2x.client.exception.BadRequestException;
import com.att.m2x.client.exception.ClientException;
import com.att.m2x.client.exception.ForbiddenException;
import com.att.m2x.client.exception.InternalServerErrorException;
import com.att.m2x.client.exception.NotFoundException;
import com.att.m2x.client.exception.ServiceUnavailableException;
import com.att.m2x.client.exception.UnauthorizedException;
import com.att.m2x.client.exception.UnprocessableEntityException;
import com.att.m2x.client.internal.RequestBuilder;
import com.att.m2x.client.internal.ResponseParser;


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

    private void handleCommonStatusCode(HttpResponse response) {
        try {
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400) {
                ErrorMessage em = getErrorMessage(response.getEntity().getContent());
                switch (code) {
                    case 400:
                        /* Sending an array when the API is expecting a hash */
                        throw new BadRequestException(em.getMessage());
                    case 401:
                        /* No valid API key provided. */
                        throw new UnauthorizedException(em.getMessage());
                    case 403:
                        /*
                        You tried to access a disabled feed,
                        or your API key does not allow to access that resource
                        */
                        throw new ForbiddenException(em.getMessage());
                    case 404:
                        /* The requested item could not be found. */
                        throw new NotFoundException(em.getMessage());
                    case 422:
                        /* Can result from sending invalid fields. */
                        throw new UnprocessableEntityException(em.toString());

                        /* Something went wrong in the M2X server. */
                    case 500:
                    case 502: //TODO
                        throw new InternalServerErrorException("M2X server is not available");
                    case 503:
                    case 504: //TODO
                        throw new ServiceUnavailableException("M2X server is not available");
                }
            }
        } catch (IOException e) {
            throw new ClientException("Unable to read response content", e);
        }

    }

    protected RequestBuilder prepare() {
        return new RequestBuilder(this.path, this.mapper);
    }

    private ErrorMessage getErrorMessage(InputStream is) throws IOException {
        return new ErrorMessage(mapper.readTree(is));
    }

    private class ErrorMessage {
        private String msg = "";
        private Map<String, String> errors = new HashMap<String, String>();

        public ErrorMessage(JsonNode root) {
            if (root.get("message") != null) {
                msg = root.get("message").asText();
            }
            if (root.get("errors") != null) {
                Iterator<Map.Entry<String, JsonNode>> nodeIterator = root.get("errors").fields();
                while (nodeIterator.hasNext()) {
                    Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodeIterator.next();
                    errors.put(entry.getKey(), entry.getValue().asText());

                }
            }
        }

        private String getMessage() {
            return msg;
        }

        @Override
        public String toString() {
            return msg + " --> " + errors.toString();
        }

    }

}

