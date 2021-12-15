package com.instagram.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;

public class SpIntegrationTest {
    @LocalServerPort
    protected int port;

    @Autowired
    protected ObjectMapper objectMapper;

    protected RestTemplate restTemplate = new RestTemplate();

    protected URI uri(String path) throws URISyntaxException {
        return new URI(format("http://localhost:%d%s", port, path));
    }
}
