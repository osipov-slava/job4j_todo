package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.repository.PriorityRepository;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class SimplePriorityService implements PriorityService {

    private final PriorityRepository priorityRepository;

    private List<Priority> priorities;

    @Override
    public List<Priority> findAll() {
        if (priorities.isEmpty()) {
            priorities = priorityRepository.findAll();
        }
        return priorities;
    }

}
