package ru.job4j.todo.repository;

import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User create(User user);

    List<User> findAllOrderById();

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findById(int id);

    Optional<User> findByLogin(String login);

    List<User> findByLikeLogin(String key);

    User update(User user);

    boolean delete(int id);
}