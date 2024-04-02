package ru.job4j.todo.service;

import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Map;

public interface PriorityService {

    List<Priority> findAll();

    Map<Integer, Priority> getAllMap();

}
