package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByListIds(List<Integer> categoryIds) {
        return categoryRepository.findByListIds(categoryIds);
    }
}
