package ru.zubov.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zubov.entity.Stat;
import ru.zubov.repo.StatRepository;

@Service
@AllArgsConstructor
@Transactional
public class StatService {
    private final StatRepository repository;

    public Stat findStat(String email) {
        return repository.findByUserEmail(email);
    }
}
