package ru.zubov.dao;

import ru.zubov.entity.Task;

import java.util.List;

public interface TaskDao extends CommonDao<Task> {

    List<Task> find(boolean completed, String email);

}
