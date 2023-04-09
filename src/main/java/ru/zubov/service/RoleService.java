package ru.zubov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zubov.entity.Role;
import ru.zubov.repo.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    public Optional<Role> findById(Long id) {
        return repository.findById(id);
    }

    public List<Role> findAll(String email) {
        return repository.findAllByUsersEmailOrderById(email);
    }

}
