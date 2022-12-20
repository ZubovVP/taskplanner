package ru.zubov.dao;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

public class UserDaoCriteria implements Crud<User> {

    @Override
    public User add(User elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.persist(elem);
        session.getTransaction().commit();
        session.close();
        return elem;
    }

    @Override
    public boolean update(User elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> root = criteriaUpdate.from(User.class);
        criteriaUpdate.set("email", elem.getEmail());
        criteriaUpdate.set("user_password", elem.getPassword());
        criteriaUpdate.set("username", elem.getUsername());
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), elem.getId()));
        int changes = session.createQuery(criteriaUpdate).executeUpdate();
        session.getTransaction().commit();
        session.close();
        return changes > 0;
    }

    @Override
    public User get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.gt(root.get("id"), id)));
        //Для добавление нескольких параметров
        //        criteriaQuery.select(root).where(criteriaBuilder.and(p1, p2));
        Query query = session.createQuery(criteriaQuery);
        session.getTransaction().commit();
        session.close();
        return (User) query.getResultList().get(0);
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
        Root<User> root = criteriaDelete.from(User.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        session.createQuery(criteriaDelete).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
