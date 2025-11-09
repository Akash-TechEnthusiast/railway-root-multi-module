package com.india.railway.model.mysql;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "train")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String train_name;
    private String train_number;
    private String start_date;
    private String end_date;
    private String noofcoaches;
    private String status; // if cancel or ontime
    private String start_station;
    private String end_station;

    // @ManyToMany(mappedBy = "trains")
    // @NotEmpty(message = "A train must have at least one passenger.")
    @ManyToMany(mappedBy = "trains", fetch = FetchType.LAZY)
    private Set<Passenger> passengers = new HashSet<>();

}