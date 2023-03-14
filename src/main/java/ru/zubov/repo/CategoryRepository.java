package ru.zubov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zubov.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}