package org.example.companyemployee.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.companyemployee.entity.Category;
import org.example.companyemployee.repository.CategoryRepository;
import org.example.companyemployee.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
