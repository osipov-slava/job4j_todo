package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImplTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task create(Task task) {
        return taskRepository.create(task);
    }

    @Override
    public boolean update(int id, Task task) {
        return taskRepository.update(task);
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
    public List<Task> findAll() {
        return taskRepository.findAllOrderById();
    }

    @Override
    public List<Task> findFinished() {
        return taskRepository.findFinishedOrderById();
    }

    @Override
    public List<Task> findInProgress() {
        return taskRepository.findInProgressOrderById();
    }
}