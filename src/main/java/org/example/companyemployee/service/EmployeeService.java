package org.example.companyemployee.service;

import org.example.companyemployee.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee save(Employee employee, MultipartFile multipartFile) throws IOException;

    Employee update(Employee employee, MultipartFile multipartFile) throws IOException;

    void deleteEmployeeImage(int employeeId);

    Optional<Employee> findById(int id);

    List<Employee> findAll();

    void delete(int id);

}
