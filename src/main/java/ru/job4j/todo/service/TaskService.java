package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task create(Task task);

    boolean update(int id, Task task);

    boolean done(int id);

    boolean deleteById(int id);

    Optional<Task> findById(int id);

    List<Task> findAll();

    List<Task> findFinished();

    List<Task> findInProgress();
}
