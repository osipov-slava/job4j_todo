package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImplTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public int create(Task task) {
        return taskRepository.create(task);
    }

    @Override
    public boolean update(int id, Task task) {
        return taskRepository.update(id, task);
    }

    @Override
    public boolean done(int id) {
        return taskRepository.done(id);
    }

    @Override
    public boolean deleteById(int id) {
        return taskRepository.deleteById(id);
    }

    @Override
    public Optional<Task> findById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public Collection<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Collection<Task> findFinished() {
        return taskRepository.findFinished();
    }

    @Override
    public Collection<Task> findInProgress() {
        return taskRepository.findInProgress();
    }
}
