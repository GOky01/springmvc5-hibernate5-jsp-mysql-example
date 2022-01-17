package com.project.springmvc.controller;

import com.project.springmvc.entity.Customer;
import com.project.springmvc.util.RetrieveUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RestCustomerControllerTest {

    private static final String SERVICE_URL
            = "http://localhost:8080/springmvc5_hibernate5_jsp_mysql_example_war_exploded/customer/rest";

    @Test
    public void getCustomers()
            throws IOException {

        HttpUriRequest request = new HttpGet(SERVICE_URL + "/customers");
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
        HttpUriRequest request = new HttpGet(SERVICE_URL + "/customers/4");
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

        Customer resource = RetrieveUtil.retrieveResourceFromResponse(
                httpResponse, Customer.class);

        assertThat(4, Matchers.is(resource.getId()));
        assertThat("Olia", Matchers.is(resource.getFirstName()));
        assertThat("Hoy", Matchers.is(resource.getLastName()));
        assertThat("oliahoy11@bigmir.net", Matchers.is(resource.getEmail()));
        assertThat("[]", Matchers.is(resource.getPhones().toString()));


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
        HttpUriRequest request = new HttpGet(SERVICE_URL + "/customers/4");
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

        Customer resource = RetrieveUtil.retrieveResourceFromResponse(
                httpResponse, Customer.class);

        resource.setEmail("olia1@bigmir.net");
        resource.setFirstName("Yulia");
        resource.setLastName("Shock");
        assertThat(4, Matchers.is(resource.getId()));
        assertThat("Yulia", Matchers.is(resource.getFirstName()));
        assertThat("Shock", Matchers.is(resource.getLastName()));
        assertThat("olia1@bigmir.net", Matchers.is(resource.getEmail()));
        assertThat("[]", Matchers.is(resource.getPhones().toString()));
    }

    @Test
    void deleteCustomer() throws IOException {
        HttpUriRequest request = new HttpDelete(SERVICE_URL + "/customers/17");
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