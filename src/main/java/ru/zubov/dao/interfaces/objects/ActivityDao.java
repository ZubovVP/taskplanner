package ru.zubov.dao.interfaces.objects;

import ru.zubov.dao.interfaces.CrudDao;
import ru.zubov.entity.Activity;
import ru.zubov.entity.Stat;
import ru.zubov.entity.User;

public interface ActivityDao extends CrudDao<Activity> {
    //todo на delete and add выбросить исключение (IllegalStateException)
    Stat getByUser(String email);

    Stat getByUser(User user);
}
