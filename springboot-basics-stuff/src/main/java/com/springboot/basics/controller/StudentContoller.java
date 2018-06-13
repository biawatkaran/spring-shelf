package com.springboot.basics.controller;

import com.springboot.basics.model.Course;
import com.springboot.basics.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StudentContoller {

    @Autowired
    StudentService studentService;

    @GetMapping("/students/{studentID}/courses")
    public List<Course> retrieveCourses(@PathVariable String studentID){

        return studentService.retrieveCourses(studentID);
    }

    @GetMapping("/students/{studentID}/courses/{courseID}")
    public Course retrieveCourseForStudent(@PathVariable String studentID, @PathVariable String courseID){

        return studentService.retrieveCourse(studentID, courseID);
    }

    @PostMapping("students/{studentID}/courses")
    public ResponseEntity<Void> registerStudentForCourse(@PathVariable String studentID, @RequestBody Course newCourse){

        Course course = studentService.addCourse(studentID, newCourse);
        if(course == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
