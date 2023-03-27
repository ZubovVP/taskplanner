package ru.zubov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.zubov.entity.Priority;

import java.util.List;

public interface PriorityRepository extends JpaRepository<Priority, Long> {

    @Query("SELECT p FROM Priority p WHERE " +
            "(:title IS NULL OR :title='' " +
            "OR LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND p.user.email=:email " +
            "ORDER by p.title ASC")
    List<Priority> findByTitle(@Param("title") String title, @Param("email") String email);

    List<Priority> findAllByUserEmailOrderByIdAsc(String email);
}
