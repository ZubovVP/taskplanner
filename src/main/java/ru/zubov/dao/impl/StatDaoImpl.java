package ru.zubov.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.zubov.dao.interfaces.objects.StatDao;
import ru.zubov.entity.Stat;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

public class StatDaoImpl implements StatDao {

    @Override
    public Stat getByUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Stat> query = session.createQuery("FROM Stat s where s.user.email = :email");
        query.setParameter("email", email);
        Stat stat = query.uniqueResult();
        session.close();
        return stat;
    }

    @Override
    public Stat getByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Stat> query = session.createQuery("FROM Stat s where s.user.id = :id");
        query.setParameter("id", user.getId());
        Stat stat = query.uniqueResult();
        session.close();
        return stat;
    }
}
