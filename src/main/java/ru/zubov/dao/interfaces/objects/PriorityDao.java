package ru.zubov.dao.interfaces.objects;

import ru.zubov.dao.interfaces.CrudDao;
import ru.zubov.dao.interfaces.FindDao;
import ru.zubov.entity.Priority;

public interface PriorityDao extends CrudDao<Priority>, FindDao<Priority> {
}
