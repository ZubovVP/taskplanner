package ru.zubov.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

import java.util.List;

public class UserHqlDao implements CommonDao<User> {
    @Override
    public User add(User elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(elem);
        session.getTransaction().commit();
        session.close();
        return elem;
    }

    @Override
    public boolean update(User elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(elem);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public User get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User result = session.get(User.class, id);
        session.close();
        return result;
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User();
        user.setId(id);
        session.remove(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM User");
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public List<User> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM User where email=:email");
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        session.close();
        return users;
    }
}
