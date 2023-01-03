package ru.zubov;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ru.zubov.entity.Role;
import ru.zubov.entity.Task;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

@Log4j2
public class Main {

    public static void main(String[] args) {
        log.info("Start Hibernate method!");

        Session session = HibernateUtil.getSessionFactory().openSession();

//        Task task = session.get(Task.class, 73L);
//
//        System.out.println(task);

//        User user1 = session.get(User.class, 24L);
//        log.info(user1);
        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        User user2 = session.get(User.class, 24L);
        log.info(user2.getTasks());

        session.close();

        //Добавление статистики по использованию L2C
        log.info("hit " + HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
        log.info("miss " + HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheMissCount());
        log.info("put " + HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCachePutCount());
        for (String s : HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheRegionNames()) {
            log.info(s);
        }
        HibernateUtil.close();
    }
}
