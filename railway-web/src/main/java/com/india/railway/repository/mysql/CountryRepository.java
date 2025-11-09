package com.india.railway.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.master.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
