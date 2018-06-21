package com.springboot.basics.controller;

import com.springboot.basics.model.Course;
import com.springboot.basics.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentContoller.class, secure = false)
public class StudentContollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    Course mockCourse = new Course("Course1","Spring","10",Arrays
            .asList("Learn Maven", "Import Project", "First Example",
                    "Second Example"));

    String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

    @Test
    public void shouldReturnCourse1() throws Exception{

        Mockito.when(studentService.retrieveCourse(Mockito.anyString(), Mockito.anyString())).thenReturn(mockCourse);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/1/courses/Course1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10\"}";
        String actual = result.getResponse().getContentAsString();
        JSONAssert.assertEquals(expected, actual,false);
    }

    @Test
    public void shouldReturnCourse1ButInList() throws Exception {

        List<Course> mockCourses = new ArrayList<>();
        mockCourses.add(mockCourse);

        Mockito.when(studentService.retrieveCourses(Mockito.anyString())).thenReturn(mockCourses);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/1}/courses").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}]";
        String actual = result.getResponse().getContentAsString();
        System.out.println(actual);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    public void shouldAddNewCourse() throws Exception {

        Mockito.when(studentService.addCourse(Mockito.anyString(),Mockito.any(Course.class))).thenReturn(mockCourse);
        MockHttpServletRequestBuilder postRequestBuilder = MockMvcRequestBuilders.post("/students/1/courses")
                                                                                 .accept(MediaType.APPLICATION_JSON)
                                                                                 .content(exampleCourseJson)
                                                                                 .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(postRequestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/students/1/courses/Course1",
                response.getHeader(HttpHeaders.LOCATION));
    }
}