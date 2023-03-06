package com.example.SpringRest.controller;

import com.example.SpringRest.dto.StudentBookDTO;
import com.example.SpringRest.model.StudentBookEntity;
import com.example.SpringRest.service.StudentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class StudentBookController {
    @Autowired
    private StudentBookService studentBookService;

    @PostMapping("/student_book/{studentId}/{bookId}")
    public String studentTakeBook(@PathVariable int studentId,
                                  @PathVariable int bookId) {
        String saveStudentBook = studentBookService.studentTakeBook(studentId, bookId);
        System.out.println(saveStudentBook);
        return saveStudentBook;
    }

    @PutMapping("/student_book/{bookId}")
    public String returnBook(@PathVariable int bookId) {
        String returnedBook = studentBookService.returnBook(bookId);
        System.out.println(returnedBook);
        return returnedBook;
    }

    @GetMapping("/student_book")
    public List<StudentBookDTO> getStudentBookList() {
        List<StudentBookEntity> studentBookList = studentBookService.getStudentBookList();
        List<StudentBookDTO> dtoList = new ArrayList<>();


        for (StudentBookEntity studentBookEntity : studentBookList) {
            StudentBookDTO dto = new StudentBookDTO();
            dto.setStudent(studentBookEntity.getStudent());
            dto.setBook(studentBookEntity.getBook());
            dto.setBookStatus(studentBookEntity.getBookStatus());
            dto.setReturnedDate(studentBookEntity.getReturnedDate());
            dto.setTakenDate(studentBookEntity.getTakenDate());
//            dto.setId(studentBookEntity.getId());
            dto.setDuration(studentBookEntity.getDuration());
            dtoList.add(dto);
        }

        return dtoList;
    }


    @GetMapping("/student_book/{id}")
    public StudentBookEntity getStudentBookById(@PathVariable Integer id){
        StudentBookEntity studentBook = studentBookService.getStudentBookById(id);
        StudentBookDTO studentBookDTO = new StudentBookDTO();

        studentBookDTO.setStudent(studentBook.getStudent());
        studentBookDTO.setBook(studentBook.getBook());
        studentBookDTO.setBookStatus(studentBook.getBookStatus());
        studentBookDTO.setTakenDate(studentBook.getTakenDate());
        studentBookDTO.setReturnedDate(studentBook.getReturnedDate());
//        studentBookDTO.setDuration(studentBook.getDuration());
//        studentBookDTO.setId(studentBook.getId());

        return studentBook;
    }

}
