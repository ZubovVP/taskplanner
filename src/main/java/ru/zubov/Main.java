package ru.zubov;

import lombok.extern.log4j.Log4j2;
import ru.zubov.dao.CommonDao;
import ru.zubov.dao.UserHqlDao;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

@Log4j2
public class Main {

    public static void main(String[] args) {
        log.info("Start Hibernate method!");

//        CommonDao<User> crud = new UserHqlDao();
//        User user = new User();
////        user.setId(26L);
//        user.setUsername("Test_3");
//        user.setPassword("111");
//        user.setEmail("3@mail.com");
//        crud.add(user);

        CommonDao<User> crud2 = new UserHqlDao();
        log.info(crud2.get(27L));

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
