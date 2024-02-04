package org.example.companyemployee.service;

import org.example.companyemployee.entity.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();

}
