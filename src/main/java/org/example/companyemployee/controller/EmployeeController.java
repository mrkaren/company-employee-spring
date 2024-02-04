package org.example.companyemployee.controller;

import lombok.RequiredArgsConstructor;
import org.example.companyemployee.entity.Employee;
import org.example.companyemployee.service.CompanyService;
import org.example.companyemployee.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    private final CompanyService companyService;


    @GetMapping("/employees")
    public String employeesPage(ModelMap modelMap) {
        modelMap.addAttribute("employees", employeeService.findAll());
        return "employees";
    }

    @GetMapping("/employees/add")
    public String addEmployeesPage(ModelMap modelMap) {
        modelMap.addAttribute("companies", companyService.findAll());
        return "addEmployee";
    }

    @PostMapping("/employees/add")
    public String addEmployees(@ModelAttribute Employee employee,
                               @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        employeeService.save(employee, multipartFile);
        return "redirect:/employees";
    }

    @PostMapping("/employees/update")
    public String updateEmployees(@ModelAttribute Employee employee,
                                  @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        employeeService.update(employee, multipartFile);
        return "redirect:/employees";
    }

    @GetMapping("/employees/update/{id}")
    public String updateEmployeesPage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Employee> byId = employeeService.findById(id);
        if (byId.isPresent()) {
            modelMap.addAttribute("companies", companyService.findAll());
            modelMap.addAttribute("employee", byId.get());
        } else {
            return "redirect:/employees";
        }
        return "updateEmployee";
    }


    @GetMapping("/employees/image/delete")
    public String deleteEmployeeImage(@RequestParam("id") int id) {
        Optional<Employee> byId = employeeService.findById(id);
        if (byId.isEmpty()) {
            return "redirect:/employees";
        } else {
            employeeService.deleteEmployeeImage(id);
            return "redirect:/employees/update/" + id;
        }
    }

}
