package ru.zubov;

import lombok.extern.log4j.Log4j2;
import ru.zubov.dao.impl.CategoryDAOImpl;
import ru.zubov.dao.impl.PriorityDaoImpl;
import ru.zubov.dao.impl.UserDaoCriteria;
import ru.zubov.dao.interfaces.CrudDao;
import ru.zubov.dao.interfaces.objects.CategoryDao;
import ru.zubov.dao.interfaces.objects.PriorityDao;
import ru.zubov.entity.Category;
import ru.zubov.entity.Priority;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;

@Log4j2
public class TestApplication {

    public static void main(String[] args) {
        //Тестирование работы приложения

        CrudDao<User> userDao = new UserDaoCriteria();
        //1) Создаём пользователя
//        User user = new User();
//        user.setUsername("Mike");
//        user.setPassword("111");
//        user.setEmail("mike@mail.com");
//        userDao.add(user);
        User user = userDao.get(30L);

        //2) Активируем пользователя
//        ActivityDao activityDao = new ActivityDaoImpl();
//        Activity activity =  activityDao.getByUser(user);
//        activity.setActivatied(true);
//        activityDao.update(activity);

//      3) Создание новой категории
        CategoryDao categoryDao = new CategoryDAOImpl();
        Category newCategory = new Category();
        newCategory.setTitle("Новая категория");
        newCategory.setUser(user);
        categoryDao.add(newCategory);

//      4) Создание нового приоритета
        PriorityDao priorityDao = new PriorityDaoImpl();
        Priority newPriority = new Priority();
        newPriority.setTitle("Новый приоритет");
        newPriority.setColor("white");
        newPriority.setUser(user);
        priorityDao.add(newPriority);


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
