package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.PriorityService;
import ru.job4j.todo.service.TaskService;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
@SessionAttributes("user")
public class TaskController {

    private final TaskService taskService;

    private final PriorityService priorityService;

    @GetMapping({"", "/filter/all"})
    public String getAll(Model model, @SessionAttribute User user) {
        model.addAttribute("tasks", taskService.findAll(user));
        return "tasks/list";
    }

    @GetMapping("/filter/finished")
    public String getFinished(Model model, @SessionAttribute User user) {
        model.addAttribute("tasks", taskService.findFinished(user));
        return "tasks/list";
    }

    @GetMapping("/filter/inprogress")
    public String getInProgress(Model model, @SessionAttribute User user) {
        model.addAttribute("tasks", taskService.findInProgress(user));
        return "tasks/list";
    }

    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("priorities", priorityService.findAll());
        return "tasks/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task, Model model, @SessionAttribute User user) {
        if (taskService.create(task, user).getId() == 0) {
            model.addAttribute("message", "Creation task was unsuccessful!");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id, @SessionAttribute User user) {
        var taskOptional = taskService.findById(id, user);
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
    public String editById(Model model, @PathVariable int id, @SessionAttribute User user) {
        model.addAttribute("priorities", priorityService.findAll());
        var optional = taskService.findById(id, user);
        if (optional.isEmpty()) {
            model.addAttribute("message", "Task with this Id not found!");
            return "errors/404";
        }
        model.addAttribute("task", optional.get());
        return "tasks/edit";
    }

    @GetMapping("/done/{id}")
    public String done(Model model, @PathVariable int id, @SessionAttribute User user) {
        if (!taskService.done(id, user)) {
            model.addAttribute("message", "Task with this Id not found!");
            return "errors/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id, @SessionAttribute User user) {
        var isDeleted = taskService.deleteById(id, user);
        if (!isDeleted) {
            model.addAttribute("message", "Task with this Id not found!");
            return "errors/404";
        }
        return "redirect:/tasks";
    }
}