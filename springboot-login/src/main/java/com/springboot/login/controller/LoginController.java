package com.springboot.login.controller;

import com.springboot.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String login(){

       return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){

        boolean isValidUser = loginService.validateUser(name, password);

        if(!isValidUser)
        {
            model.put("errorMessage", "Invalid login");
            return "login";
        }

        model.put("name", name);

        return "welcome";
    }

}
