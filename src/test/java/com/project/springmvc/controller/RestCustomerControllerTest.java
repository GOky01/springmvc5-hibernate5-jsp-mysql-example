package com.project.springmvc.controller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RestCustomerControllerTest {

    private static final String SERVICE_URL
            = "http://localhost:8080/spring_mvc_war_exploded/rest";

    @Test
    public void getCustomers()
            throws IOException {

        HttpUriRequest request = new HttpGet(SERVICE_URL + "/listCustomers");
        String jsonMimeType = "application/json";

        HttpResponse httpResponse = HttpClientBuilder
                .create()
                .build()
                .execute(request);

        assertEquals(httpResponse
                .getStatusLine()
                .getStatusCode(), HttpStatus.SC_OK);

        String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
    }

    @Test
    void getCustomer() throws IOException {
        HttpUriRequest request = new HttpGet(SERVICE_URL + "/listCustomers/4");
        String jsonMimeType = "application/json";

        HttpResponse httpResponse = HttpClientBuilder
                .create()
                .build()
                .execute(request);

        assertEquals(httpResponse
                .getStatusLine()
                .getStatusCode(), HttpStatus.SC_OK);

        String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);


    }

    @Test
    void saveCustomer() throws IOException {
        HttpUriRequest badRequest = new HttpPost(SERVICE_URL);

        HttpResponse badResponse = HttpClientBuilder
                .create()
                .build()
                .execute(badRequest);

        assertNotEquals(badResponse
                .getStatusLine()
                .getStatusCode(), HttpStatus.SC_OK);
        assertEquals(badResponse
                .getStatusLine()
                .getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void replaceCustomer() throws IOException {
        HttpUriRequest request = new HttpGet(SERVICE_URL + "/listCustomers/4");
        String jsonMimeType = "application/json";

        HttpResponse httpResponse = HttpClientBuilder
                .create()
                .build()
                .execute(request);

        assertEquals(httpResponse
                .getStatusLine()
                .getStatusCode(), HttpStatus.SC_OK);

        String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
    }

    @Test
    void deleteCustomer() throws IOException {
        HttpUriRequest request = new HttpDelete(SERVICE_URL + "/listCustomers/23");
        String jsonMimeType = "text/plain";

        HttpResponse httpResponse = HttpClientBuilder
                .create()
                .build()
                .execute(request);

        assertEquals(httpResponse
                .getStatusLine()
                .getStatusCode(), HttpStatus.SC_OK);

        String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
    }
}