package ru.zubov.dao.interfaces.objects;

import ru.zubov.entity.Stat;
import ru.zubov.entity.User;

public interface StatDao {

    Stat getByUser(String email);

    Stat getByUser(User user);
}
