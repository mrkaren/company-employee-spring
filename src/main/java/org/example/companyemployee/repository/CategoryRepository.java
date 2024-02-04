package org.example.companyemployee.repository;

import org.example.companyemployee.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
