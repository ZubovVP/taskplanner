package ru.zubov.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Category> findById(@RequestBody String email) {
        return categoryService.findAll(email);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("id param must be NULL", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null && category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param : title", HttpStatus.NOT_ACCEPTABLE);

        }
        return ResponseEntity.ok(categoryService.add(category));
    }
}
