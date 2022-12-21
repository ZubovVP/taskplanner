package ru.zubov.dao;

import org.hibernate.Session;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

public class UserHqlDao implements Crud<User> {
    @Override
    public User add(User elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.persist(elem);
        session.close();
        return elem;
    }

    @Override
    public boolean update(User elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.merge(elem);
        session.close();
        return true;
    }

    @Override
    public User get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        User result = session.get(User.class, id);
        session.close();
        return result;
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        User user = new User();
        user.setId(id);
        session.remove(user);
        session.close();
    }
}
