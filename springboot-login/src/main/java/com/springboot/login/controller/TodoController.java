package com.springboot.login.controller;

import com.springboot.login.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/list-todos")
    public String showTodos(ModelMap model){

        String name = (String) model.get("name");
        model.put("todos", todoService.retrieveTodos(name));
        return "list-todos";
    }
}
