package com.india.railway.model.mysql;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EmployeeTreeDTO {

    private Long id;
    private String name;

    private List<EmployeeTreeDTO> subordinates;

    public EmployeeTreeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
        this.subordinates = new ArrayList<>(); // ✅ Initialize list
    }

    public EmployeeTreeDTO(Employee emp) {
        this.id = emp.getId();
        this.name = emp.getName();

        // Initialize and populate subordinates list
        this.subordinates = new ArrayList<>();
        if (emp.getSubordinates() != null && !emp.getSubordinates().isEmpty()) {
            for (Employee sub : emp.getSubordinates()) {
                this.subordinates.add(new EmployeeTreeDTO(sub)); // recursion
            }
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<EmployeeTreeDTO> getSubordinates() {
        return subordinates;
    }

    // Getters and setters
}
