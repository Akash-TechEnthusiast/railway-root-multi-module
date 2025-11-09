package com.india.railway.controller.passenger;

import com.india.railway.service.mysql.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.mysql.Address;
import com.india.railway.model.mysql.Passenger;
import com.india.railway.model.mysql.Train;
import com.india.railway.service.mysql.PassengerServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passenger")
@RequiredArgsConstructor
// Allow React Frontend
public class PassengerController_Mysql {

    private final PassengerServiceImpl passengerServiceImpl;

    // Single constructor — Spring injects the dependency

    @PostMapping("/create")
    public String addPassenger(@RequestBody Passenger passenger) throws IllegalAccessException {
        return passengerServiceImpl.addPassenger(passenger);
    }

    @GetMapping("/fetch_all_passenger")
    public List<Passenger> getAllPassengers() {
        return passengerServiceImpl.getAllPassengers();

    }

    @GetMapping("getPassengerById/{id}")
    public Optional<Passenger> getPassengerById(@PathVariable Long id) {
        return passengerServiceImpl.getPassenger(id);
    }

    @PutMapping("updatePassenger/{id}")
    public String updatePassenger(@RequestBody Passenger passenger) {
        return passengerServiceImpl.updatePassenger(passenger);
    }

    @GetMapping("/bypagewise")
    public ResponseEntity<Page<Passenger>> getAllPassengers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<Passenger> passengers = passengerServiceImpl.getAllPassengers(page, size, sortBy);
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{passengerId}/trains")
    public ResponseEntity<Page<Train>> getTrainsByPassengerId(
            @PathVariable Long passengerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<Train> trainsPage = passengerServiceImpl.getTrainsByPassengerId(passengerId, page, size, sortBy);
        return ResponseEntity.ok(trainsPage);
    }

    @GetMapping("/{passengerId}/address")
    public ResponseEntity<Page<Address>> getAddressByPassengerId(
            @PathVariable Long passengerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<Address> addressList = passengerServiceImpl.findAddressByPassengerId(passengerId, page, size, sortBy);
        return ResponseEntity.ok(addressList);
    }

}
