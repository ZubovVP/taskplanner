package ru.zubov.dao.interfaces;

import java.util.List;

public interface FindDao<T> {
    List<T> findAll();

    List<T> findAll(String email);
}
