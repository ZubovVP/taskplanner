package ru.zubov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.zubov.entity.Category;
import ru.zubov.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/findById")
    public Category findById(@RequestParam("id") Long id) {
        return categoryService.findById(id).orElse(null);
    }

    @PostMapping("/all")
    public List<Category> findById(@RequestParam String email) {
        return categoryService.findAll(email);
    }
}
