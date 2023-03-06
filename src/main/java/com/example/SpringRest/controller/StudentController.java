package com.example.SpringRest.controller;

import com.example.SpringRest.model.Student;
import com.example.SpringRest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public String saveStudent(@RequestBody Student student) {
        String saveStudent = studentService.saveStudent(student);
        System.out.println(saveStudent);
        return saveStudent;
    }

    @GetMapping("/student")
    public List<Student> getStudentList() {
        List<Student> studentList = studentService.getStudentList();
        if (studentList.isEmpty()) {
            return null;
        }
        return studentList;
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        System.out.println(student);
        return student;
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudentById(@PathVariable Integer id) {
        String delete = studentService.deleteStudentById(id);
        System.out.println(delete);
        return delete;
    }

    @PutMapping("/student/{id}")
    public String putStudentById(@RequestBody Student student, @PathVariable Integer id) {
        Student studentById = getStudentById(id);
        if (studentById==null) {
            return "Student not found";
        }

        student.setId(id);
        String update = studentService.updateStudent(student);
        return update;
    }
}
