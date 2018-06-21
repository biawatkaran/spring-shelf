package com.springboot.login.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validateUser(String user, String password) {

        System.out.println("User: " + user + " allowed to enter");

        if(user.equals("1")){
            return false;
        }

        return true;
    }
}
