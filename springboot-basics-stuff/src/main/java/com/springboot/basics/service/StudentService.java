package com.springboot.basics.service;

import com.springboot.basics.model.Course;
import com.springboot.basics.model.Student;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class StudentService {

    private static List<Student> students = new ArrayList<>();

    static{

        Course course1 = new Course("Course1", "Spring", "10 Steps", Arrays.asList("Learn Maven"));

        Course course2 = new Course("Course2", "Spring MVC", "10 Examples",
                Arrays.asList("Learn Maven", "Import Project", "First Example",
                        "Second Example"));
        Course course3 = new Course("Course3", "Spring Boot", "6K Students",
                Arrays.asList("Learn Maven", "Learn Spring",
                        "Learn Spring MVC", "First Example", "Second Example"));
        Course course4 = new Course("Course4", "Maven",
                "Most popular maven course on internet!", Arrays.asList(
                "Pom.xml", "Build Life Cycle", "Parent POM",
                "Importing into Eclipse"));

        Student ranga = new Student("Student1", "Ranga Karanam",
                "Hiker, Programmer and Architect", new ArrayList<>(Arrays
                .asList(course1, course2, course3, course4)));

        Student satish = new Student("Student2", "Satish T",
                "Hiker, Programmer and Architect", new ArrayList<>(Arrays
                .asList(course1, course2, course3, course4)));

        students.add(ranga);
        students.add(satish);
    }

    public List<Student> retrieveAllStudents(){

        return  students;
    }

    public Student retrieveStudent(String studentID){

        for(Student student : students){
            if(student.getId().equals(studentID)){
                return student;
            }
        }
        return null;
    }

    public List<Course> retrieveCourses(String studentID){

        Student student = retrieveStudent(studentID);
        return  student.getCourses();
    }

    public Course retrieveCourse(String studentID, String courseID){

        Student student = retrieveStudent(studentID);
        List<Course> courses = student.getCourses();

        for(Course course: courses){
            if (courseID.equals(course.getId())){

                return course;
            }
        }
        return  null;
    }

    public Course addCourse(String studentID, Course course){

        Student student = retrieveStudent(studentID);
        if(student == null) {
            return null;
        }

        String randomId = new BigInteger(130, new Random()).toString(32);
        course.setId(randomId);

        student.getCourses().add(course);

        return course;
    }

}
