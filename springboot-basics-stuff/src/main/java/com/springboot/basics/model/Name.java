package com.springboot.basics.model;

import java.util.Objects;

public class Name {

    private String firstName;
    private String surName;

    public Name(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) &&
                Objects.equals(surName, name.surName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, surName);
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}
