package com.klef.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.dev.entity.Student;
import com.klef.dev.service.StudentService;

@RestController
@RequestMapping("/studentapi/")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @GetMapping("/")
    public String home() 
    {
        return "Docker Backend is running";
    }
    @GetMapping("/docker")
    public String docker() 
    {
        return "Docker Full Stack Deployment Demo with git Actions";
    }

    

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        Student existing = studentService.getStudentById(student.getId());
        if (existing != null) {
            Student updatedStudent = studentService.updateStudent(student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. Student with ID " + student.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        Student existing = studentService.getStudentById(id);
        if (existing != null) {
            studentService.deleteStudentById(id);
            return new ResponseEntity<>("Student with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Student with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
