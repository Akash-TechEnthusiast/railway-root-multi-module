package com.india.railway.repository.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.india.railway.model.master.State;
import com.india.railway.model.master.StateDTO;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    List<State> findByCountryId(Long countryId);

}
