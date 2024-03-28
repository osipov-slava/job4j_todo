package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepository {

    int create(Task task);

    boolean update(int id, Task task);

    boolean deleteById(int id);

    Optional<Task> findById(int id);

    Collection<Task> findAll();
}
