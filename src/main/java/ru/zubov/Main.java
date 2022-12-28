package ru.zubov;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;


@Log4j2
public class Main {


    public static void main(String[] args) {
        log.info("Start Hibernate method!");

        Session session = HibernateUtil.getSessionFactory().openSession();

        User user1 = session.get(User.class, 24);
        log.info(user1);
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        User user2 = session.get(User.class, 24);
        log.info(user2);
        session.close();


        HibernateUtil.close();
    }
}
