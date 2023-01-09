package ru.zubov.dao.interfaces.objects;

import ru.zubov.dao.interfaces.CrudDao;
import ru.zubov.entity.Activity;
import ru.zubov.entity.User;

public interface ActivityDao extends CrudDao<Activity> {
    Activity getByUser(String email);

    Activity getByUser(User user);
}
