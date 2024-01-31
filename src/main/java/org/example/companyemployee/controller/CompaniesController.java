package org.example.companyemployee.controller;

import org.example.companyemployee.entity.Company;
import org.example.companyemployee.repository.CompanyRepository;
import org.example.companyemployee.security.SpringUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompaniesController {


    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public String companiesPage(ModelMap modelMap) {
        List<Company> companies = companyRepository.findAll();
        modelMap.put("companies", companies);
        return "companies";
    }

    @GetMapping("/companies/add")
    public String addCompanyPage() {
        return "addCompany";
    }

    @PostMapping("/companies/add")
    public String addCompany(@ModelAttribute Company company, @AuthenticationPrincipal SpringUser springUser) {
        company.setUser(springUser.getUser());
        companyRepository.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/companies/delete/{id}")
    public String deleteCompany(@PathVariable("id") int id) {
        companyRepository.deleteById(id);
        return "redirect:/companies";
    }

}
