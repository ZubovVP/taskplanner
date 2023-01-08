package ru.zubov.dao.impl;

import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.zubov.dao.interfaces.CrudDao;
import ru.zubov.dao.interfaces.FindDao;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

import java.util.List;

public class UserDaoCriteria implements FindDao<User>, CrudDao<User> {

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
        Query query = session.createQuery(criteriaQuery);
        session.getTransaction().commit();
        List<User> users = (List<User>) query.getResultList();
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
        Query query = session.createQuery(criteriaQuery);
        session.getTransaction().commit();
        List<User> users = (List<User>) query.getResultList();
        session.close();
        return users;
    }
}
