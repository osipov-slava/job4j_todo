package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;

import java.util.List;

@Repository
@AllArgsConstructor
public class HbnPriorityRepository implements PriorityRepository {

    private final CrudRepository crudRepository;

    @Override
    public List<Priority> findAll() {
        return crudRepository.query("FROM Priority", Priority.class);
    }
}
