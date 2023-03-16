package ru.zubov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zubov.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    /**
     * Поиск категорий пользователя (по email) сортировка по названию
     * @param email - email пользователя
     * @return лист списка категорий в отсортированном виде
     */
    List<Category> findByUserEmailOrderByTitleAsc(String email);
}