package com.springboot.basics.service;

import com.springboot.basics.entity.Student;
import com.springboot.basics.exception.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StudentDBService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> retrieveAllStudents(){

        return studentRepository.findAll();
    }

    public Student retrieveStudent(Long id){

        Optional<Student> student = studentRepository.findById(id);

        if(!student.isPresent()){
            throw new StudentNotFoundException("Student not found :" + id);
        }

        return student.get();
    }

    public void deleteStudent(@PathVariable long id){

        studentRepository.deleteById(id);
    }

    public Student saveStudent(Student newStudent){

        Student savedStudent = studentRepository.save(newStudent);
        return savedStudent;
    }

    public Student updateStudent(Student student, long id){

        Student studentFound = retrieveStudent(id);

        student.setId(id);
        Student updatedStudent = studentRepository.save(student);
        return updatedStudent;
    }
}
