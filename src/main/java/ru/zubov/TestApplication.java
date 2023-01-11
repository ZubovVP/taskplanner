package ru.zubov;

import lombok.extern.log4j.Log4j2;
import ru.zubov.dao.impl.UserDaoCriteria;
import ru.zubov.dao.interfaces.CrudDao;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

@Log4j2
public class TestApplication {

    public static void main(String[] args) {

        CrudDao<User> userDao = new UserDaoCriteria();
        //Создаём пользователя
//        User user = new User();
//        user.setUsername("Mike");
//        user.setPassword("111");
//        user.setEmail("mike@mail.com");
//        userDao.add(user);

        //Активируем пользователя
//        ActivityDao activityDao = new ActivityDaoImpl();
//        Activity activity =  activityDao.getByUser(user);
//        activity.setActivatied(true);
//        activityDao.update(activity);

        User user = userDao.get(30L);


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
