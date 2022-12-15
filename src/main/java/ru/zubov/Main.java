package ru.zubov;

import org.hibernate.Session;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.getTransaction().begin();

        User user = new User();
        user.setEmail("newEmail@gmail.com");
        user.setUsername("User");
        user.setPassword("123456");

        session.persist(user);

        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }
}
