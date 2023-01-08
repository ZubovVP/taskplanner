package ru.zubov.dao.interfaces;

public interface CrudDao<T> {

    T add(T elem);

    boolean update(T elem);

    T get(Long id);

    void delete(Long id);
}
