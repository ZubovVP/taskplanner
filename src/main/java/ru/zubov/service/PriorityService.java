package ru.zubov.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zubov.entity.Priority;
import ru.zubov.repo.PriorityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PriorityService {
    private final PriorityRepository priorityRepository;

    public Priority add(Priority priority) {
        return priorityRepository.save(priority);
    }

    public void update(Priority priority) {
        priorityRepository.save(priority);
    }

    public void delete(Long id) {
        Priority priority = new Priority();
        priority.setId(id);
        priorityRepository.delete(priority);
    }

    public Optional<Priority> findById(Long id) {
        return priorityRepository.findById(id);
    }

    public List<Priority> findAll(String email) {
        return priorityRepository.findAllByUserEmailOrderByIdAsc(email);
    }

    public List<Priority> findByTitle(String title, String email) {
        return priorityRepository.findByTitle(title, email);
    }
}
