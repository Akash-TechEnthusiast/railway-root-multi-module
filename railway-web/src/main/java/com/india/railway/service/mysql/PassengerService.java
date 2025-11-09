package com.india.railway.service.mysql;

import java.util.List;
import java.util.Optional;

import com.india.railway.model.mysql.Passenger;

public interface PassengerService {

    // Method to get customer by its Id
    Optional<Passenger> getPassenger(Long id);

    // Method to add a new Customer
    // into the database
    String addPassenger(Passenger passenger) throws IllegalAccessException;

    // Method to update details of a Customer
    String updatePassenger(Passenger passenger);

    List<Passenger> getAllPassengers();

}
