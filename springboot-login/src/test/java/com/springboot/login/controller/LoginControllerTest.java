package com.springboot.login.controller;

import com.springboot.login.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LoginController.class, secure = false)
public class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LoginService loginService;

    @Test
    public void shouldShowLoginPageOnInvalidCredentials() throws Exception {

        Mockito.when(loginService.validateUser(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login");
        MvcResult result = mockMvc.perform(requestBuilder.param("name", "SpringBoot").param("password","dummy")).andReturn();

        String actualPage = result.getResponse().getForwardedUrl();
        String expected = "login.jsp";

        assertTrue(actualPage.contains(expected));
        assertTrue(result.getModelAndView().getModel().containsKey("errorMessage"));
        String invalidCredential = (String)result.getModelAndView().getModel().get("errorMessage");
        assertTrue(invalidCredential.contains("Invalid"));

    }

    @Test
    public void shouldShowWelcomePageOnValidCredentials() throws Exception {

        Mockito.when(loginService.validateUser(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login");
        MvcResult result = mockMvc.perform(requestBuilder.param("name", "SpringBoot").param("password","dummy")).andReturn();

        String actualPage = result.getResponse().getForwardedUrl();
        String expected = "welcome.jsp";

        assertTrue(actualPage.contains(expected));

    }
}