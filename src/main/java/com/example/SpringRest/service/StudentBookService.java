package com.example.SpringRest.service;

import com.example.SpringRest.dto.StudentBookDTO;
import com.example.SpringRest.enums.BookStatus;
import com.example.SpringRest.model.Book;
import com.example.SpringRest.model.Student;
import com.example.SpringRest.model.StudentBookEntity;
import com.example.SpringRest.repository.BookRepository;
import com.example.SpringRest.repository.StudentBookRepository;
import com.example.SpringRest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentBookService {
    @Autowired
    private StudentBookRepository studentBookRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;

    public String studentTakeBook(int studentId, int bookId) {
        Book book = bookRepository.getBookById(bookId);
        if(book==null){
            return "Book not found";
        }

        Student student = studentRepository.getStudentById(studentId);
        if(student==null){
            return "Student not found";
        }

        StudentBookEntity studentBookEntity = new StudentBookEntity();
        studentBookEntity.setStudent(studentRepository.getStudentById(studentId));
        studentBookEntity.setBook(bookRepository.getBookById(bookId));
        studentBookEntity.setTakenDate(LocalDate.now());
        studentBookEntity.setBookStatus(BookStatus.ON_HAND);

        int takeBook = studentBookRepository.takeBook(studentBookEntity);
        if(takeBook==0){
            return "ERROR";
        }
        return "Saved";
    }

    public String returnBook(int bookId) {
        Book book = bookRepository.getBookById(bookId);
//        if(book==null){
//            return "Book not found";
//        }

        int returnBook = studentBookRepository.returnBook(book);
//        if(returnBook==0){
//            return "ERROR";
//        }
//        return "Updated";
        return null;
    }

    public List<StudentBookEntity> getStudentBookList() {
        return studentBookRepository.getStudentBookList();
    }

    public StudentBookEntity getStudentBookById(Integer id) {
        if(id<=0){
            return null;
        }

        return studentBookRepository.getStudentBookBy(id);
    }
}
