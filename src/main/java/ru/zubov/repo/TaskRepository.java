package ru.zubov.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zubov.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}