package com.example.SpringRest.repository;

import com.example.SpringRest.dto.StudentBookDTO;
import com.example.SpringRest.enums.BookStatus;
import com.example.SpringRest.model.Book;
import com.example.SpringRest.model.StudentBookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class StudentBookRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public int takeBook(StudentBookEntity studentBookEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer save =(Integer) session.save(studentBookEntity);

        transaction.commit();
        session.close();
        return save;
    }

    public int returnBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("Update StudentBookEntity set book_status= :status, duration=(CURRENT_DATE-takenDate)," +
                                                    "returned_date =:returnedDate where id =id");

        query.setParameter("status", BookStatus.RETURNED.name());
        query.setParameter("returnedDate", LocalDate.now());
//        query.setParameter("id", 2);

        int update = query.executeUpdate();
        transaction.commit();
        session.close();

        return update;
    }

    public List<StudentBookEntity> getStudentBookList() {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("FROM StudentBookEntity");

        List<StudentBookEntity> resultList = query.getResultList();
        return resultList;
    }

    public StudentBookEntity getStudentBookBy(Integer id) {
        Session session = sessionFactory.openSession();

        StudentBookEntity studentBook = session.get(StudentBookEntity.class, id);
        return studentBook;
    }
}

















