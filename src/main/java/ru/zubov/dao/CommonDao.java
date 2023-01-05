package ru.zubov.dao;

import java.util.List;

public interface CommonDao<T> extends Crud<T> {
    List<T> findAll();

    List<T> findAll(String email);
}
