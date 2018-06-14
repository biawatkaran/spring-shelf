package com.springboot.basics.controller;

import com.springboot.basics.SpringbootBasicsStuffApplication;
import com.springboot.basics.model.Course;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootBasicsStuffApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentContollerIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void getServiceIntegrationTest() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null,headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/students/Student1/courses/Course1"), HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void postServiceIntegrationTest() throws JSONException {

        Course course = new Course("postIntegrationTestID","postIntegrationTest","Post Integration Test Description", Arrays.asList("Spring Boot Microservices"));

        HttpEntity<Course> entity = new HttpEntity<>(course, headers);

        ResponseEntity<Course> response = restTemplate.exchange(createURLWithPort("/students/Student1/courses"), HttpMethod.POST, entity, Course.class);

        String location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
        assertTrue(location.contains("/students/Student1/courses"));

    }

    private String createURLWithPort(String uri){
        return "http://localhost:" + port + uri;
    }
}