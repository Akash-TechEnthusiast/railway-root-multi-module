package com.india.railway.repository.mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.india.railway.model.mysql.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    // @EntityGraph(attributePaths = "trains")
    // Optional<Passenger> findById(Long id);
    @Query("SELECT p FROM Passenger p JOIN FETCH p.trains WHERE p.id = :id")
    Optional<Passenger> findByIdWithTrains(@Param("id") Long id);

    @Query("SELECT p FROM Passenger p JOIN FETCH p.trains WHERE p.id = :id")
    Optional<Passenger> findPassengerWithTrains(@Param("id") Long id);

}