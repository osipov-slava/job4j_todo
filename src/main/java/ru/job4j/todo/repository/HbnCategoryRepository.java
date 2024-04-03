package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class HbnCategoryRepository implements CategoryRepository {

    private final CrudRepository crudRepository;

    @Override
    public List<Category> findAll() {
        return crudRepository.query("FROM Category", Category.class);
    }

    @Override
    public List<Category> findByListIds(List<Integer> categoryIds) {
        return crudRepository.query("FROM Category c WHERE c.id IN (:categoryIds)", Category.class,
                Map.of("categoryIds", categoryIds));
    }
}
