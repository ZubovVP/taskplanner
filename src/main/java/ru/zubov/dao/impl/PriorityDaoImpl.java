package ru.zubov.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.zubov.dao.interfaces.objects.PriorityDao;
import ru.zubov.entity.Priority;
import ru.zubov.utils.HibernateUtil;

import java.util.List;

public class PriorityDaoImpl implements PriorityDao {
    @Override
    public Priority add(Priority elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(elem);
        session.getTransaction().commit();
        session.close();
        return elem;
    }

    @Override
    public boolean update(Priority elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(elem);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Priority get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Priority result = session.get(Priority.class, id);
        session.close();
        return result;
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Priority Priority = new Priority();
        Priority.setId(id);
        session.remove(Priority);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Priority> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Priority> query = session.createQuery("FROM Priority");
        List<Priority> priorities = query.getResultList();
        session.close();
        return priorities;
    }

    @Override
    public List<Priority> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Priority> query = session.createQuery("FROM Priority p where p.user.email like :email");
        query.setParameter("email", "%" + email + "%");
        List<Priority> priorities = query.getResultList();
        session.close();
        return priorities;
    }
}
