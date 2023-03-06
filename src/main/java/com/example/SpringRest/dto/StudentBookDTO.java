package com.example.SpringRest.dto;

import com.example.SpringRest.enums.BookStatus;
import com.example.SpringRest.model.Book;
import com.example.SpringRest.model.Student;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentBookDTO {
    private Integer id;
    private Student student;
    private Book book;
    private LocalDate takenDate;
    private LocalDate returnedDate;
    private BookStatus bookStatus;
    private Integer duration;
}
