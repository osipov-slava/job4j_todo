package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {

    private final TaskRepository taskRepository;

    private final CategoryService categoryService;

    @Override
    public Task create(Task task, User user, List<Integer> categoryIds) {
        task.setUser(user);
        task.setCategories(categoryService.findByListIds(categoryIds));
        return taskRepository.create(task);
    }

    @Override
    public boolean update(int id, Task task, List<Integer> categoryIds) {
        categoryIds.forEach(c -> task.getCategories().add(new Category(c, null)));
        return taskRepository.update(task);
    }

    @Override
    public boolean done(int id, User user) {
        return taskRepository.done(id, user);
    }

    @Override
    public boolean deleteById(int id, User user) {
        return taskRepository.deleteById(id, user);
    }

    @Override
    public Optional<Task> findById(int id, User user) {
        return taskRepository.findById(id, user);
    }

    @Override
    public List<Task> findAll(User user) {
        return taskRepository.findAllOrderById(user);
    }

    @Override
    public List<Task> findFinished(User user) {
        return taskRepository.findFinishedOrderById(user);
    }

    @Override
    public List<Task> findInProgress(User user) {
        return taskRepository.findInProgressOrderById(user);
    }

}
