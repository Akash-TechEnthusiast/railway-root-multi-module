package com.india.railway.model.mysql;



import jakarta.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

   // @ManyToMany(mappedBy = "departments")
  //  private Set<Passenger> passengers = new HashSet<>();

    // Constructors, getters, setters

    public Department() {
    }

    public Department(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
