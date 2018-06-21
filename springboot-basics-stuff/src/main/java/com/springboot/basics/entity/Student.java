package com.springboot.basics.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@ApiModel(description="Student Table")
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ApiModelProperty(notes="Name should have atleast 2 characters")
    @Size(min = 2, message = "Name should have atleast 2 characters")
    private String name;

    @NotNull
    @Size(min = 7, message = "Name should have atleast 7 characters")
    private String passportNumber;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(passportNumber, student.passportNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, passportNumber);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}
