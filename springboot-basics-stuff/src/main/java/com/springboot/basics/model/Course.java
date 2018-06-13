package com.springboot.basics.model;

import java.util.List;
import java.util.Objects;

public class Course {

    private String id;
    private String name;
    private String description;
    private List<String> steps;

    public Course() {

    }

    public Course(String id, String name, String description, List<String> steps) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", steps=" + steps +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(name, course.name) &&
                Objects.equals(description, course.description) &&
                Objects.equals(steps, course.steps);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, steps);
    }
}
