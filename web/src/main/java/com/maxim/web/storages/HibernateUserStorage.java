package com.maxim.web.storages;

import com.maxim.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Максим on 16.09.2016.
 */
@Repository
public class HibernateUserStorage implements Storage<User> {

    private final SessionFactory factory;

    public HibernateUserStorage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public Collection<User> values(){
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from User").list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    public int add(final User user){
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return (Integer)session.save(user);
        } finally {
            tx.commit();
            session.close();
        }
    }

    public void edit(final User user){
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(user);
        } finally {
            tx.commit();
            session.close();
        }
    }

    public void delete(final int id){
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            User userForDelete=new User();
            userForDelete.setId(id);
            session.delete(userForDelete);
        } finally {
            tx.commit();
            session.close();
        }
    }

    public User get(final int id){
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return (User) session.get(User.class, id);
        } finally {
            tx.commit();
            session.close();
        }
    }

    public void close(){
        this.factory.close();
    }
}
