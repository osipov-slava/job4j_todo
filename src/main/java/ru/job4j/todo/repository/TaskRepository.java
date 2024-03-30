package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task create(Task task);

    List<Task> findAllOrderById();

    Optional<Task> findById(int id);

    List<Task> findFinishedOrderById();

    List<Task> findInProgressOrderById();

    boolean update(Task task);

    boolean done(int id);

    boolean deleteById(int id);
}