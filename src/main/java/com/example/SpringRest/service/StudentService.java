package com.example.SpringRest.service;

import com.example.SpringRest.model.Student;
import com.example.SpringRest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public String saveStudent(Student student) {
        String check = checkStudentParameters(student);
        if (check != null) {
            return check;
        }

        int save = studentRepository.saveStudent(student);
        if(save==0){
            return "ERROR";
        }
        return "Student saved";
    }

    private String checkStudentParameters(Student student) {
        if (student.getName().isEmpty() || student.getName().isBlank()) {
            return "Student name is required";
        }

        if (student.getSurname().isEmpty() || student.getSurname().isBlank()) {
            return "Student surname is required";
        }

        if (student.getPhone().isEmpty() || student.getPhone().isBlank()) {
            return "Student phone is required";
        }

        if (!student.getPhone().startsWith("+998")) {
            student.setPhone("+998" + student.getPhone());
        }

        if(student.getPhone().length()!=13){
            return "Phone number is wrong";
        }


        return null;
    }

    public List<Student> getStudentList() {
        return studentRepository.studentList();
    }

    public Student getStudentById(Integer id) {
        if(id<=0){
            return null;
        }

        Student student = studentRepository.getStudentById(id);
        return student;
    }

    public String deleteStudentById(Integer id) {
        if(id<=0){
            return "ID must be positive";
        }

        Student student = getStudentById(id);
        if(student==null){
            return "Student not found";
        }

        int delete = studentRepository.deleteStudentById(id);

        if(delete==0){
            return "ERROR";
        }
        return "Student deleted";
    }

    public String updateStudent(Student student) {
        String check = checkStudentParameters(student);
        if(check!=null){
            return check;
        }

        int update = studentRepository.updateStudent(student);
        if(update==0){
            return "ERROR";
        }
        return "Student updated";
    }
}
