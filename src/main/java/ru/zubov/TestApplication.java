package ru.zubov;

import lombok.extern.log4j.Log4j2;
import ru.zubov.dao.impl.*;
import ru.zubov.dao.interfaces.CrudDao;
import ru.zubov.dao.interfaces.objects.CategoryDao;
import ru.zubov.dao.interfaces.objects.PriorityDao;
import ru.zubov.dao.interfaces.objects.StatDao;
import ru.zubov.dao.interfaces.objects.TaskDao;
import ru.zubov.entity.*;
import ru.zubov.utils.HibernateUtil;

import java.time.LocalDate;

@Log4j2
public class TestApplication {
    //        СЦЕНАРИЙ (один из множества вариантов - можете придумать свой):
//        создаем пользователя (триггеры создадут сразу же тестовые данные)
//        активируем пользователя (поле activated)
//        создаем новую категорию
//        создаем новый приоритет
//        создаем несколько новых задач (помимо тестовых) с новыми категорией и приоритетом
//        завершаем задачу
//        удаляем задачу
//        считываем статистику по любой категории пользователя
//        считываем общую статистику пользователя


    public static void main(String[] args) {
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

//      5) Создание задачи
        TaskDao taskDao = new TaskDaoImpl();
        Task newTask = new Task();
        newTask.setUser(user);
        newTask.setCategory(newCategory);
        newTask.setTitle("Новая задача");
        newTask.setCreateDate(LocalDate.now());
        newTask.setPriority(newPriority);
        newTask.setCompleted(false);
        taskDao.add(newTask);

//      6) Завершение задачи
        newTask.setCompleted(true);
        taskDao.update(newTask);

//      7) Удаление задачи
        taskDao.delete(newTask.getId());

//      8) Получение статистики общей
        StatDao statDao = new StatDaoImpl();
        Stat statByUser = statDao.getByUser(user);
        log.info(statByUser);

//      9) Получение статистики по категории
        log.info(newCategory.getCompletedCount());


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
