package org.example.companyemployee.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "company")
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "company")
    private List<Employee> employeeList;

    @ManyToOne
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date openedDate;

    @ManyToMany
    @JoinTable(name = "company_category",
    joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}
