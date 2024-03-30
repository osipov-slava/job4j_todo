package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping({"", "/filter/all"})
    public String getAll(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/list";
    }

    @GetMapping("/filter/finished")
    public String getFinished(Model model) {
        model.addAttribute("tasks", taskService.findFinished());
        return "tasks/list";
    }

    @GetMapping("/filter/inprogress")
    public String getInProgress(Model model) {
        model.addAttribute("tasks", taskService.findInProgress());
        return "tasks/list";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, Model model) {
        if (taskService.create(task).getId() == 0) {
            model.addAttribute("message", "Creation task was unsuccessful!");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Task with this Id not found!");
            return "errors/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/one";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model) {
        if (!taskService.update(task.getId(), task)) {
            model.addAttribute("message", "Update task was unsuccessful!");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("update/{id}")
    public String editById(Model model, @PathVariable int id) {
        var optional = taskService.findById(id);
        if (optional.isEmpty()) {
            model.addAttribute("message", "Task with this Id not found!");
            return "errors/404";
        }
        model.addAttribute("task", optional.get());
        return "tasks/edit";
    }

    @GetMapping("/done/{id}")
    public String done(Model model, @PathVariable int id) {
        if (!taskService.done(id)) {
            model.addAttribute("message", "Task with this Id not found!");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = taskService.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Task with this Id not found!");
            return "errors/404";
        }
        return "redirect:/tasks";
    }
}