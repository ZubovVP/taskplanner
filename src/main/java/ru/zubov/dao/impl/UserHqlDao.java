package ru.zubov.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.zubov.dao.interfaces.objects.UserDao;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

import java.util.List;

public class UserHqlDao implements UserDao {

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
        Query<User> query = session.createQuery("FROM User");
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public List<User> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<User> query = session.createQuery("FROM User where email like :email");
        query.setParameter("email", "%" + email + "%");
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public User getByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<User> query = session.createQuery("FROM User where email = :email");
        query.setParameter("email", email);
        User user = query.uniqueResult();
        session.close();
        return user;
    }
}
