package ru.job4j.todo.repository;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task create(Task task);

    List<Task> findAllOrderById(User user, List<Integer> categoryIds);

    Optional<Task> findById(int id, User user);

    List<Task> findFinishedOrderById(User user, List<Integer> categoryIds);

    List<Task> findInProgressOrderById(User user, List<Integer> categoryIds);

    boolean update(Task task);

    boolean done(int id, User user);

    boolean deleteById(int id, User user);
}
