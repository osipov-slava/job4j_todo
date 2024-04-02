package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.repository.PriorityRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimplePriorityService implements PriorityService {

    private final PriorityRepository priorityRepository;

    private List<Priority> priorities;

    private Map<Integer, Priority> priorityMap;

    @Override
    public List<Priority> findAll() {
        if (priorities.isEmpty()) {
            priorities = priorityRepository.findAll();
            priorityMap = findAll().stream()
                    .collect(Collectors.toMap(Priority::getId, Function.identity()));
        }
        return priorities;
    }

    @Override
    public Map<Integer, Priority> getAllMap() {
        if (priorityMap.isEmpty()) {
            findAll();
        }
        return priorityMap;
    }
}
