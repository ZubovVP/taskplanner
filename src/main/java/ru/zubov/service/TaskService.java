package ru.zubov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zubov.entity.Task;
import ru.zubov.repo.TaskRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }
}
