package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    private List<Category> categories;

    private Map<Integer, Category> categoryMap = Collections.emptyMap();

    @Override
    public List<Category> findAll() {
        if (categories.isEmpty()) {
            categories = categoryRepository.findAll();
            categoryMap = findAll().stream()
                    .collect(Collectors.toMap(Category::getId, Function.identity()));
        }
        return categories;
    }

    @Override
    public Map<Integer, Category> getAllMap() {
        if (categoryMap.isEmpty()) {
            findAll();
        }
        return categoryMap;
    }

    @Override
    public List<Category> getListFromIds(List<Integer> categoryIds) {
        List<Category> categories = new ArrayList<>();
        for (Integer i : categoryIds) {
            Category category = new Category(i, categoryMap.get(i).getName());
            categories.add(category);
        }
        return categories;
    }

}
