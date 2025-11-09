package com.india.railway.model.master;

import jakarta.persistence.*;

@Entity
@Table(name = "district")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    // 👇 Many states belong to one country

    // Constructors
    public District() {
    }

    public District(String name, String code, State state) {
        this.name = name;
        this.code = code;
        this.state = state;
    }

    public District(Long id, String code, String name) {
        this.id = id;
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

    public State getState() {
        return state;
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

    public void setState(State state) {
        this.state = state;
    }
}
