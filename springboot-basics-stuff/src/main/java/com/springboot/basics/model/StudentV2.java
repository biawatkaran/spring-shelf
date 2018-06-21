package com.springboot.basics.model;

import java.util.List;
import java.util.Objects;

public class StudentV2 {

    private Long id;
    private Name name;
    private String description;
    private List<Course> courses;

    public StudentV2() {
    }

    public StudentV2(Long id, Name name, String description, List<Course> courses) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
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
        StudentV2 student = (StudentV2) o;
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
