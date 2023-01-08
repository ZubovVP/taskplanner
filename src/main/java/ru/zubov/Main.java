package ru.zubov;

import lombok.extern.log4j.Log4j2;
import ru.zubov.dao.impl.CategoryDAOImpl;
import ru.zubov.dao.impl.TaskDaoImpl;
import ru.zubov.dao.impl.UserDaoCriteria;
import ru.zubov.dao.impl.UserHqlDao;
import ru.zubov.dao.interfaces.FindDao;
import ru.zubov.dao.interfaces.objects.TaskDao;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

@Log4j2
public class Main {

    public static void main(String[] args) {
        log.info("Start Hibernate method!");

        CategoryDAOImpl categoryCommonDao = new CategoryDAOImpl();
        categoryCommonDao.delete(92L);

        log.info(categoryCommonDao.findAll("duke@gmail.com"));

        FindDao<User> crud = new UserDaoCriteria();
//        User user = new User();
//        user.setId(27L);
//        user.setUsername("Test_4");
//        user.setPassword("111");
//        user.setEmail("4@mail.com");
//        crud.update(user);

        log.info(crud.findAll("mail"));


        FindDao<User> crud2 = new UserHqlDao();
        log.info(crud2.findAll("mail"));


        TaskDao taskDao = new TaskDaoImpl();
        log.info(taskDao.find(true, "1111@mail.com"));

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
