package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

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
    public List<Task> findAllOrderById() {
        try {
            return crudRepository.query("FROM Task ORDER BY id ASC", Task.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Task> findById(int id) {
        try {
            return crudRepository.optional(
                    "FROM Task where id = :id", Task.class,
                    Map.of("id", id)
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Task> findFinishedOrderById() {
        try {
            return crudRepository.query("FROM Task WHERE done = true ORDER BY id ASC", Task.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public List<Task> findInProgressOrderById() {
        try {
            return crudRepository.query("FROM Task WHERE done = false ORDER BY id ASC", Task.class);
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
    public boolean done(int id) {
        try {
            var result = crudRepository.run(
                    "UPDATE Task SET done = :done WHERE id = :id",
                    Map.of("id", id,
                            "done", true)
            );
            return result > 0;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            var result = crudRepository.run(
                    "DELETE Task WHERE id = :id",
                    Map.of("id", id)
            );
            return result > 0;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
}