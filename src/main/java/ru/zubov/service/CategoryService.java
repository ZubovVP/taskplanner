package ru.zubov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zubov.entity.Category;
import ru.zubov.repo.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public void update(Category category) {
        categoryRepository.save(category);
    }

    public void delete(Long id) {
        Category category = new Category();
        category.setId(id);
        categoryRepository.delete(category);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findAll(String email) {
        return categoryRepository.findByUserEmailOrderByTitleAsc(email);
    }

    public List<Category> findByTitle(String title, String email) {
        return categoryRepository.findByTitle(title, email);
    }
}
