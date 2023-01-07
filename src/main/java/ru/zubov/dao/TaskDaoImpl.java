package ru.zubov.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.zubov.entity.Task;
import ru.zubov.utils.HibernateUtil;

import java.util.List;

public class TaskDaoImpl implements TaskDao {

    @Override
    public Task add(Task elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(elem);
        session.getTransaction().commit();
        session.close();
        return elem;
    }

    @Override
    public boolean update(Task elem) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(elem);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Task get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Task result = session.get(Task.class, id);
        session.close();
        return result;
    }

    @Override
    public void delete(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Task task = new Task();
        task.setId(id);
        session.remove(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Task> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Task");
        List<Task> tasks = query.getResultList();
        session.close();
        return tasks;
    }

    @Override
    public List<Task> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Task t where t.user.email like :email");
        query.setParameter("email", "%" + email + "%");
        List<Task> tasks = query.getResultList();
        session.close();
        return tasks;
    }

    @Override
    public List<Task> find(boolean completed, String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Task t where t.user.email like :email and t.completed = :completed");
        query.setParameter("email", "%" + email + "%");
        query.setParameter("completed", completed);
        List<Task> tasks = query.getResultList();
        session.close();
        return tasks;
    }
}
