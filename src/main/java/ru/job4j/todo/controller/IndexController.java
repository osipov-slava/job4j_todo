package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.todo.repository.TaskRepository;

@Controller
@AllArgsConstructor
public class IndexController {

    private final TaskRepository taskRepository;

    @GetMapping({"/", "/index"})
    public String getIndex() {
        return "index";
    }

}