package ru.zubov.dao.interfaces.objects;

import ru.zubov.dao.interfaces.CrudDao;
import ru.zubov.dao.interfaces.FindDao;
import ru.zubov.entity.Task;

import java.util.List;

public interface TaskDao extends FindDao<Task>, CrudDao<Task> {

    List<Task> find(boolean completed, String email);

}
