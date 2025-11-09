package com.india.railway.model.master;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<District> district;

    // 👇 Many states belong to one country

    // Constructors
    public State() {
    }

    public State(String name, String code, Country country) {
        this.name = name;
        this.code = code;
        this.country = country;
    }

    public State(String code, String name) {
        this.code = code;
        this.name = name;

    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Country getCountry() {
        return country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
