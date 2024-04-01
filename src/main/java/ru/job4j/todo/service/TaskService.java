package ru.job4j.todo.service;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task create(Task task, User user);

    boolean update(int id, Task task);

    boolean done(int id, User user);

    boolean deleteById(int id, User user);

    Optional<Task> findById(int id, User user);

    List<Task> findAll(User user);

    List<Task> findFinished(User user);

    List<Task> findInProgress(User user);
}
