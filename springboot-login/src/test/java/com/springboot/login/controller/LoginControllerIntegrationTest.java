package com.springboot.login.controller;

import com.springboot.login.LoginApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoginApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void shouldShowLoginPage(){

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response= restTemplate.exchange(createURLWithPort("/login"), HttpMethod.GET, entity, String.class);
        assertTrue("Page Not Displayed", HttpStatus.OK.equals(response.getStatusCode()));
    }

    @Test
    public void shouldAllowValidUser(){

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response= restTemplate.exchange(createURLWithPort("/login"), HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
    }

    private String createURLWithPort(String uri){
        return "http://localhost:" + port + uri;
    }
}
