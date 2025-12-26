package com.india.railway.service.mysql;

import java.util.List;
import java.util.Optional;

import com.india.railway.dto.PassengerRequestDTO;
import com.india.railway.exception.ApiResponse;
import com.india.railway.model.mysql.Passenger;
import org.springframework.http.ResponseEntity;

public interface PassengerService {

    // Method to get customer by its Id
    Optional<Passenger> getPassenger(String id);

    // Method to add a new Customer
    // into the database
    ResponseEntity<ApiResponse<Passenger>> addPassenger(PassengerRequestDTO dto) throws IllegalAccessException;

    // Method to update details of a Customer
    ResponseEntity<ApiResponse<Passenger>> updatePassenger(PassengerRequestDTO dto);

    List<Passenger> getAllPassengers();

    ResponseEntity<ApiResponse<Passenger>> deletePassenger(String id);

}
