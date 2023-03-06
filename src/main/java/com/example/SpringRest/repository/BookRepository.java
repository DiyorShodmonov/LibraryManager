package com.example.SpringRest.repository;

import com.example.SpringRest.config.Config;
import com.example.SpringRest.model.Book;
import com.example.SpringRest.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class BookRepository {

    @Autowired
    private SessionFactory sessionFactory;
    public int saveBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer save =(Integer) session.save(book);

        transaction.commit();
        session.close();
        return save;
    }

    public List<Book> bookList() {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("From Book");
        List<Book> bookList = query.getResultList();
        session.close();


        if(bookList.isEmpty()){
            return null;
        }
        return bookList;
    }

    public Book getBookById(Integer id) {
        Session session = sessionFactory.openSession();

        Book book = session.get(Book.class, id);
        return book;
    }

    public int deleteBookById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(getBookById(id));

        transaction.commit();
        session.close();
        return 1;
    }

    public int updateBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        Query query = session.createQuery("Update Book set title = :title ," +
                "author =:author, published_year= :published_year where id = :id");

        query.setParameter("title", book.getTitle());
        query.setParameter("author", book.getAuthor());
        query.setParameter("published_year", book.getPublishYear());
        query.setParameter("id", book.getId());

        int update = query.executeUpdate();

        transaction.commit();
        session.close();
        return update;
    }
}
