package ru.zubov.dao.interfaces.objects;

import ru.zubov.dao.interfaces.CrudDao;
import ru.zubov.dao.interfaces.FindDao;
import ru.zubov.entity.User;

public interface UserDao extends CrudDao<User>, FindDao<User> {

    User getByEmail(String email);
}
