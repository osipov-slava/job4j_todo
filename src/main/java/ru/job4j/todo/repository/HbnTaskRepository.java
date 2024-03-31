package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class HbnTaskRepository implements TaskRepository {

    private final CrudRepository crudRepository;

    @Override
    public Task create(Task task) {
        try {
            crudRepository.run(session -> session.persist(task));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return task;
    }

    @Override
    public Optional<Task> findById(int id, User user) {
        try {
            return crudRepository.optional(
                    "FROM Task WHERE id = :id AND todo_user = :user", Task.class,
                    Map.of("id", id,
                            "user", user.getId())
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Task> findAllOrderById(User user) {
        try {
            return crudRepository.query(
                    "FROM Task WHERE todo_user = :user ORDER BY id ASC", Task.class,
                    Map.of("user", user.getId())
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<Task> findFinishedOrderById(User user) {
        try {
            return crudRepository.query(
                    "FROM Task WHERE done = true AND todo_user = :user ORDER BY id ASC", Task.class,
                    Map.of("user", user.getId())
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<Task> findInProgressOrderById(User user) {
        try {
            return crudRepository.query(
                    "FROM Task WHERE done = false AND todo_user = :user ORDER BY id ASC", Task.class,
                    Map.of("user", user.getId())
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public boolean update(Task task) {
        try {
            crudRepository.run(session -> session.merge(task));
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean done(int id, User user) {
        try {
            var result = crudRepository.run(
                    "UPDATE Task SET done = :done WHERE id = :id AND todo_user = :user",
                    Map.of("id", id,
                            "done", true,
                            "user", user.getId())
            );
            return result > 0;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id, User user) {
        try {
            var result = crudRepository.run(
                    "DELETE Task WHERE id = :id AND todo_user = :user",
                    Map.of("id", id,
                            "user", user.getId())
            );
            return result > 0;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
}