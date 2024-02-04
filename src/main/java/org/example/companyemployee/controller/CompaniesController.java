package org.example.companyemployee.controller;

import lombok.RequiredArgsConstructor;
import org.example.companyemployee.entity.Company;
import org.example.companyemployee.security.SpringUser;
import org.example.companyemployee.service.CategoryService;
import org.example.companyemployee.service.CompanyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompaniesController {

    private final CompanyService companyService;
    private final CategoryService categoryService;

    @GetMapping("/companies")
    public String companiesPage(ModelMap modelMap) {
        List<Company> companies = companyService.findAll();
        modelMap.put("companies", companies);
        return "companies";
    }

    @GetMapping("/companies/add")
    public String addCompanyPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "addCompany";
    }

    @PostMapping("/companies/add")
    public String addCompany(@ModelAttribute Company company, @AuthenticationPrincipal SpringUser springUser) {
        company.setUser(springUser.getUser());
        companyService.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/companies/delete/{id}")
    public String deleteCompany(@PathVariable("id") int id) {
        companyService.delete(id);
        return "redirect:/companies";
    }

}
