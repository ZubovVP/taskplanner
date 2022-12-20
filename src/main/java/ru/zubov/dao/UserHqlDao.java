package ru.zubov.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

import java.util.List;

public class UserHqlDao implements Crud<User> {
    @Override
    public User add(User elem) {

        return null;
    }

    @Override
    public boolean update(User elem) {
        return false;
    }

    @Override
    public User get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query<User> query = session.createQuery("FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        User result = query.uniqueResult();
        session.close();
        return result;
    }

    @Override
    public void delete(Long id) {

    }
}
