package ru.zubov.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.zubov.dao.interfaces.objects.CategoryDao;
import ru.zubov.entity.Category;
import ru.zubov.utils.HibernateUtil;

import java.util.List;

public class CategoryDAOImpl implements CategoryDao {

    @Override
    public Category add(Category elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(elem);
        session.getTransaction().commit();
        session.close();
        return elem;
    }

    @Override
    public boolean update(Category elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(elem);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Category get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Category result = session.get(Category.class, id);
        session.close();
        return result;
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Category task = new Category();
        task.setId(id);
        session.remove(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Category> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Category");
        List<Category> tasks = query.getResultList();
        session.close();
        return tasks;
    }

    @Override
    public List<Category> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Category с where с.user.email like :email");
        query.setParameter("email", "%" + email + "%");
        List<Category> categories = query.getResultList();
        session.close();
        return categories;
    }
}
