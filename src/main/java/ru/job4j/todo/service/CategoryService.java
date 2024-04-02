package ru.job4j.todo.service;

import ru.job4j.todo.model.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    List<Category> findAll();

    Map<Integer, Category> getAllMap();

    List<Category> getListFromIds(List<Integer> categoryIds);
}
