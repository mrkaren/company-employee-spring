package org.example.companyemployee.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.companyemployee.entity.Employee;
import org.example.companyemployee.repository.EmployeeRepository;
import org.example.companyemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Value("${picture.upload.directory}")
    private String uploadDirectory;

    @Override
    public Employee save(Employee employee, MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadDirectory, picName);
            multipartFile.transferTo(file);
            employee.setPicName(picName);
        }
        return employeeRepository.save(employee);
    }


    @Override
    public Employee update(Employee employee, MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String picName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadDirectory, picName);
            multipartFile.transferTo(file);
            employee.setPicName(picName);
        } else {
            Optional<Employee> fromDB = findById(employee.getId());
            employee.setPicName(fromDB.get().getPicName());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeImage(int employeeId) {
        Optional<Employee> byId = employeeRepository.findById(employeeId);
        Employee employee = byId.get();
        String picName = employee.getPicName();
        if (picName != null) {
            employee.setPicName(null);
            employeeRepository.save(employee);
            File file = new File(uploadDirectory, picName);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Override
    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }
}
