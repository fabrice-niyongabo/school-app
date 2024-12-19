package com.fabrice.studentregistration.studentregistration.controllers;

import com.fabrice.studentregistration.studentregistration.dto.RegisterStudentRequest;
import com.fabrice.studentregistration.studentregistration.exceptions.NotFoundException;
import com.fabrice.studentregistration.studentregistration.models.Student;
import com.fabrice.studentregistration.studentregistration.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("*")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getStudents(){
       return studentRepository.findAll();
    }

    @PostMapping
    public Student addStudent(@RequestBody RegisterStudentRequest studentRequest){
        Student newStudent = new Student(
                studentRequest.getFirstname(),
                studentRequest.getLastname(),
                studentRequest.getEmail()
        );
        return studentRepository.save(newStudent);
    }

    @DeleteMapping("/{studentId}")
    public Map<String, String> deleteStudent(@PathVariable Long studentId){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException("Student not found"));

        studentRepository.delete(student);
        Map<String, String> response  = new HashMap<>();
        response.put("message", "Student with ID: "+student.getId()+" has been deleted!");
        return response;
    }

}
