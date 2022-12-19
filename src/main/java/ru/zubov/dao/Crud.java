package ru.zubov.dao;

public interface Crud<T> {

    T add(T elem);

    boolean update(T elem);

    T get(Long id);

    void delete(Long id);
}
