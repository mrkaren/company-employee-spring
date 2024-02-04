package org.example.companyemployee.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.companyemployee.entity.Category;
import org.example.companyemployee.entity.Company;
import org.example.companyemployee.entity.Employee;
import org.example.companyemployee.repository.CompanyRepository;
import org.example.companyemployee.repository.EmployeeRepository;
import org.example.companyemployee.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Company save(Company company) {
        List<Category> categories = company.getCategories();
        List<Category> finalCategories = new ArrayList<>();
        if (categories != null) {
            for (Category category : categories) {
                if (category.getId() != 0) {
                    finalCategories.add(category);
                }
            }
        }
        company.setCategories(finalCategories);
        return companyRepository.save(company);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public void delete(int companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            List<Employee> employeeList = company.getEmployeeList();
            employeeRepository.deleteAll(employeeList);
            companyRepository.deleteById(companyId);
        }
    }
}
