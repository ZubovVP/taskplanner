package ru.zubov.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.zubov.dao.interfaces.objects.UserDao;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

import javax.persistence.criteria.*;
import java.util.List;

public class UserDaoCriteria implements UserDao {

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
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> root = criteriaUpdate.from(User.class);
        criteriaUpdate.set("email", elem.getEmail());
        criteriaUpdate.set("username", elem.getUsername());
        criteriaUpdate.set("password", elem.getPassword());
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), elem.getId()));
        int changes = session.createQuery(criteriaUpdate).executeUpdate();
        session.getTransaction().commit();
        session.close();
        return changes > 0;
    }

    @Override
    public User get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"), id)));
        //Для добавление нескольких параметров
        //        criteriaQuery.select(root).where(criteriaBuilder.and(p1, p2));
        Query query = session.createQuery(criteriaQuery);
        session.getTransaction().commit();
        User user = (User) query.getResultList().get(0);
        session.close();
        return user;
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
        Root<User> root = criteriaDelete.from(User.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        session.createQuery(criteriaDelete).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        Query<User> query = session.createQuery(criteriaQuery);
        session.getTransaction().commit();
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public List<User> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.like(root.get("email"), "%" + email + "%")));
        Query<User> query = session.createQuery(criteriaQuery);
        session.getTransaction().commit();
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public User getByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.equal(root.get("email"), email)));
        Query<User> query = session.createQuery(criteriaQuery);
        session.getTransaction().commit();
        User user = query.uniqueResult();
        session.close();
        return user;
    }
}
