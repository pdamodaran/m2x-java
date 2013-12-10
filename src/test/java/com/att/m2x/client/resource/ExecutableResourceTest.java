package com.att.m2x.client.resource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.att.m2x.client.exception.ForbiddenException;
import com.att.m2x.client.exception.NotFoundException;
import com.att.m2x.client.exception.UnauthorizedException;
import com.att.m2x.client.internal.RequestBuilder;
import com.att.m2x.client.internal.ResponseParser;
import com.att.m2x.client.util.BaseMockTest;


public class ExecutableResourceTest extends BaseMockTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    private static final String PATH = "http://localhost:8080";

    private ExecutableResource resource;
    private RequestBuilder request;


    @Before
    public void setUp() {
        HttpClient client = HttpClientBuilder.create().build();
        ObjectMapper mapper = new ObjectMapper();

        resource = new ExecutableResource(PATH, client, mapper);
        request = new RequestBuilder(PATH, mapper);
    }

    @Test
    public void verifyHappyPath() {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(200).withBody(prop("happy"))));

        ResponseParser parser = resource.action(request.get());

        TestEntry entry = parser.as(TestEntry.class);

        assertThat(entry, is(notNullValue()));
        assertThat(entry.getTest(), is("pass"));
    }

    @Test(expected = ForbiddenException.class)
    public void verifyForbiddenException() {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(403).withBody(prop("exception.403"))));
        resource.action(request.get());
        fail();
    }

    @Test(expected = NotFoundException.class)
    public void verifyNotFoundException() {
        stubFor(get(urlEqualTo("/")).willReturn(aResponse().withStatus(404).withBody(prop("exception.404"))));
        resource.action(request.get());
        fail();
    }

    @Override
    protected String getPathToProperties() {
        return "mock/executable-samples.xml";
    }

    private static class ExecutableResource extends com.att.m2x.client.internal.resource.ExecutableResource {

        private ExecutableResource(String path, HttpClient client, ObjectMapper mapper) {
            super(path, client, mapper);
        }

        public ResponseParser action(RequestBuilder requestBuilder) {
            return this.execute(requestBuilder);
        }

    }

}
