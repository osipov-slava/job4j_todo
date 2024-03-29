package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {

    int create(Task task);

    boolean update(int id, Task task);

    boolean done(int id);

    boolean deleteById(int id);

    Optional<Task> findById(int id);

    Collection<Task> findAll();

    Collection<Task> findFinished();

    Collection<Task> findInProgress();
}
