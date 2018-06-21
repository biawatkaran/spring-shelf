package com.springboot.basics.controller;

import com.springboot.basics.entity.Student;
import com.springboot.basics.exception.StudentNotFoundException;
import com.springboot.basics.model.Course;
import com.springboot.basics.service.StudentDBService;
import com.springboot.basics.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class StudentContoller {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentDBService studentDBService;

    @GetMapping("/students")
    public List<Student> retrieveAllStudents(){

        return studentDBService.retrieveAllStudents();
    }

    @GetMapping("/students/{id}")
    @ApiOperation(value = "Find student by id", nickname = "retrieveStudent",notes = "Also returns a link to retrieve all students with rel - all-students")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Result.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure") })
    public Resource<Student> retrieveStudent(@PathVariable long id){

        Student student = studentDBService.retrieveStudent(id);

        Resource<Student> resource = new Resource<>(student);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllStudents());
        resource.add(linkTo.withRel("all-students"));

        return resource;
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable long id){

        studentDBService.deleteStudent(id);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> saveStudent(@Valid @RequestBody Student student){

        Student savedStudent = studentDBService.saveStudent(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable long id){

        try {

            studentDBService.updateStudent(student, id);
            return ResponseEntity.noContent().build();

        }catch (StudentNotFoundException e){

            return ResponseEntity.notFound().build();
        }
    }

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
