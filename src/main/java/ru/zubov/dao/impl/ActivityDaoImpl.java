package ru.zubov.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.zubov.dao.interfaces.objects.ActivityDao;
import ru.zubov.entity.Activity;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

public class ActivityDaoImpl implements ActivityDao {

    @Override
    public Activity add(Activity elem) {
        throw new IllegalStateException("Cant create new activity");
    }

    @Override
    public boolean update(Activity elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(elem);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Activity get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Activity result = session.get(Activity.class, id);
        session.close();
        return result;
    }

    @Override
    public void delete(Long id) {
        throw new IllegalStateException("Cant delete new activity");
    }

    @Override
    public Activity getByUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Activity> query = session.createQuery("FROM Activity a where a.user.email = :email");
        query.setParameter("email", email);
        Activity activity = query.uniqueResult();
        session.close();
        return activity;
    }

    @Override
    public Activity getByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Activity> query = session.createQuery("FROM Activity a where a.user.id = :id");
        query.setParameter("id", user.getId());
        Activity activity = query.uniqueResult();
        session.close();
        return activity;
    }
}
