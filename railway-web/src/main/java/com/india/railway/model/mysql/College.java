package com.india.railway.model.mysql;

import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Data
@Table
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5)
    private String name;

    // @Column(precision = 12, scale = 4) // Decimal type with precision 12 and
    // scale 4
    private Double amount; // all department people salary cost

    @Column(nullable = false) // Column must not be null
    private Boolean active;

    @Temporal(TemporalType.DATE) // Date type
    @Column(name = "established_year")
    private Date established_year;

    @OneToMany
    @JoinTable(name = "college_departments", joinColumns = @JoinColumn(name = "college_id"), inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> department = new HashSet<>();

    // Constructors, getters, setters

    public College() {
    }

    public College(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Department> getDepartment() {
        return department;
    }

    public void setDepartment(Set<Department> books) {
        this.department = books;
    }

    public void addDepartment(Department book) {
        department.add(book);
    }

    public void removeDepartment(Department book) {
        department.remove(book);
    }
}
