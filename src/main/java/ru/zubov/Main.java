package ru.zubov;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ru.zubov.utils.HibernateUtil;


@Log4j2
public class Main {


    public static void main(String[] args) {
        log.info("Start Hibernate method!");

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.getTransaction().begin();


        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }
}
