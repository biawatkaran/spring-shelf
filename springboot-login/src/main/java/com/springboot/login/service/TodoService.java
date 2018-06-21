package com.springboot.login.service;

import com.springboot.login.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "in28Minutes", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "in28Minutes", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "in28Minutes", "Learn Hibernate", new Date(),
                false));
    }

    public List<Todo> retrieveTodos(String user) {

        List<Todo> filteredTodos = new ArrayList<Todo>();

        Predicate<Todo> userCheck = todo -> todo.getUser().equals(user);

        todos.forEach( item -> {
            if(userCheck.test(item)){
                filteredTodos.add(item);
            }
        });

        return filteredTodos;
    }
}
