package com.india.railway.repository.mysql;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.mysql.Address;
import com.india.railway.model.mysql.Train;
import com.india.railway.model.mysql.TrainNameProjection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // @Query("SELECT a FROM Address a JOIN a.passenger p WHERE p.id =
    // :passengerId")

    // @Query("SELECT a FROM Address a WHERE a.passenger.id = :passengerId")
    @Query("SELECT a FROM Passenger p JOIN p.address a WHERE p.id = :passengerId")
    Page<Address> findAddressListByPassengerId(@Param("passengerId") Long passengerId, Pageable pageable);

}