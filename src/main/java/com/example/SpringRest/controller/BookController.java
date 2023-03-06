package com.example.SpringRest.controller;

import com.example.SpringRest.model.Book;
import com.example.SpringRest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<?> saveBook(@RequestBody Book book) {
//        String saveBook = bookService.saveBook(book);
//        System.out.println(saveBook);
//        return saveBook;
//
//        Book book1 = bookService.saveBook(book);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/book")
    public List<Book> getBookList() {
        List<Book> bookList = bookService.getBookList();
        if (bookList.isEmpty()) {
            return null;
        }
        return bookList;
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable Integer id) {
        Book book = bookService.getBookById(id);
        System.out.println(book);
        return book;
    }

    @DeleteMapping("/book/{id}")
    public String deleteBookById(@PathVariable Integer id) {
        String delete = bookService.deleteBookById(id);
        System.out.println(delete);
        return delete;
    }

    @PutMapping("/book/{id}")
    public String putBookById(@RequestBody Book book, @PathVariable Integer id) {
        Book bookById = getBookById(id);
        if (bookById==null) {
            return "Book not found";
        }

        book.setId(id);
        String update = bookService.updateBook(book);
        return update;
    }
}
