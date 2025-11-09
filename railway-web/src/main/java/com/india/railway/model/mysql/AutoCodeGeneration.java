package com.india.railway.model.mysql;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class AutoCodeGeneration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String entity_name;

    private String prefix;

    private String year;

    private long current_value;

    // Constructors, getters, setters

}
