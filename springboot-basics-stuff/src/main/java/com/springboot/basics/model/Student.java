package com.springboot.basics.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

public class Student {

    private Long id;
    private String name;
    private String description;
    private String passportNumber;
    private List<Course> courses;

    public Student() {
    }

    public Student(Long id, String name, String description, List<Course> courses) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(description, student.description) &&
                Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, courses);
    }
}
