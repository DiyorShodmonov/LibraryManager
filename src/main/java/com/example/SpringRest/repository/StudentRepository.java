package com.example.SpringRest.repository;

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
public class StudentRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public int saveStudent(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        student.setCreatedDate(LocalDate.now());
        Integer save =(Integer) session.save(student);

        transaction.commit();
        session.close();
        return save;
    }

    public List<Student> studentList() {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("From Student");
        List<Student> studentList = query.getResultList();
        session.close();


        if(studentList.isEmpty()){
            return null;
        }
        return studentList;
    }

    public Student getStudentById(Integer id) {
        Session session = sessionFactory.openSession();

        Student student = session.get(Student.class, id);
        return student;
    }

    public int deleteStudentById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        session.delete(getStudentById(id));

        Query query = session.createQuery("DELETE from Student where id= :id");
        query.setParameter("id", id);

        session.delete(getStudentById(id));
        transaction.commit();
        session.close();
        return 1;
    }

    public int updateStudent(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        Query query = session.createQuery("Update Student set name = :name ," +
                "surname =:surname, phone= :phone where id = :id");

        query.setParameter("name", student.getName());
        query.setParameter("surname", student.getSurname());
        query.setParameter("phone", student.getPhone());
        query.setParameter("id", student.getId());

        int update = query.executeUpdate();

        transaction.commit();
        session.close();
        return update;
    }
}
