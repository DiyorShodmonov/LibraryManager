package com.example.SpringRest.service;

import com.example.SpringRest.model.Book;
import com.example.SpringRest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    public String saveBook(Book book) {
        String check = checkBookParameters(book);
        if (check != null) {
            return check;
        }

        int save = bookRepository.saveBook(book);
        if(save==0){
            return "ERROR";
        }
        return "Book saved";
    }

    private String checkBookParameters(Book book) {
        if (book.getTitle().isEmpty() || book.getTitle().isBlank()) {
            return "Student name is required";
        }

        if (book.getAuthor().isEmpty() || book.getAuthor().isBlank()) {
            return "Student surname is required";
        }

        if (book.getPublishYear()<=0) {
            return "Student phone is required";
        }

        return null;
    }


    public List<Book> getBookList() {
        return bookRepository.bookList();
    }

    public Book getBookById(Integer id) {
        if(id<=0){
            return null;
        }

        Book book = bookRepository.getBookById(id);
        return book;
    }

    public String deleteBookById(Integer id) {
        if(id<=0){
            return "ID must be positive";
        }

        Book book = getBookById(id);
        if(book==null){
            return "Book not found";
        }

        int delete = bookRepository.deleteBookById(id);

        if(delete==0){
            return "ERROR";
        }
        return "Book deleted";
    }

    public String updateBook(Book book) {
        String check = checkBookParameters(book);
        if(check!=null){
            return check;
        }

        int update = bookRepository.updateBook(book);
        if(update==0){
            return "ERROR";
        }
        return "Book updated";
    }
}
