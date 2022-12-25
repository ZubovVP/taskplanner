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

        session.getTransaction().begin();

//        Role role = session.get(Role.class, 1);

        User user = session.get(User.class, 21);
        System.out.println(user.getEmail());
        System.out.println(user.getPriorities());
//        System.out.println(user.getRoles());


        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }
}
