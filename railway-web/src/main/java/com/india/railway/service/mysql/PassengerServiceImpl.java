package com.india.railway.service.mysql;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.india.railway.exception.EntityNotFoundException;
import com.india.railway.exception.NoSuchEmployeeExistsException;
import com.india.railway.exception.NoSuchPassengerExistsException;
import com.india.railway.exception.PassengerAlreadyExistsException;
import com.india.railway.model.mysql.Address;
import com.india.railway.model.mysql.Passenger;
import com.india.railway.model.mysql.Train;
import com.india.railway.model.mysql.TrainNameProjection;
import com.india.railway.repository.mysql.AddressRepository;
import com.india.railway.repository.mysql.PassengerRepository;
import com.india.railway.repository.mysql.TrainRepository;

import jakarta.transaction.Transactional;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    AutoCodeGeneratorService autoCodeGeneratorService;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Optional<Passenger> getPassenger(Long id) {
        // TODO Auto-generated method stub
        Passenger passenger = passengerRepository.findPassengerWithTrains(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        List<TrainNameProjection> traisnList = trainRepository.findTrainsByPassengerId(id);

        Set<Train> trains = new HashSet<>();
        for (TrainNameProjection train : traisnList) {
            Train t = new Train();
            t.setTrain_name(train.getTrainName());
            t.setTrain_number(train.getTrainNumber());
            trains.add(t);

        }
        passenger.setTrains(trains);

        return Optional.ofNullable(passenger);

        // return Optional.ofNullable(passengerRepository.findById(id)
        // .orElseThrow(() -> new NoSuchPassengerExistsException("NO PASSENGER PRESENT
        // WITH ID = " + id)));

        // throw new UnsupportedOperationException("Unimplemented method
        // 'getPassenger'");
    }

    @Override
    public String addPassenger(Passenger passenger) throws IllegalAccessException {
        // passengerRepository.save(passenger);
        // return "";
        // throw new UnsupportedOperationException("Unimplemented method
        // 'addPassenger'");

        Passenger existingPassenger = passengerRepository.findById(passenger.getId()).orElse(null);
        if (existingPassenger == null) {

            // long nextvalueis =
            // autoCodeGeneratorService.generateNextId("passenger_entity", 1);
            // System.out.println(nextvalueis);

            if (passenger != null && (passenger.getTrains() == null || passenger.getTrains().isEmpty())) {
                throw new EntityNotFoundException("Trains list could not be empty ");
            }

            autoCodeGeneratorService.generateId(passenger);

            passengerRepository.save(passenger);
            return "Passenger added successfully";
        } else
            throw new PassengerAlreadyExistsException("Passenger already exists!!");
    }

    @Override
    public String updatePassenger(Passenger passenger) {
        Passenger existingPassenger = passengerRepository.findById(passenger.getId()).orElse(null);
        if (existingPassenger == null)
            throw new NoSuchEmployeeExistsException("No Such Employee exists!!");
        else {
            existingPassenger.setName(passenger.getName());
            existingPassenger.setAddress(passenger.getAddress());
            passengerRepository.save(existingPassenger);
            return "Record updated Successfully";
        }
    }

    @Override
    public List<Passenger> getAllPassengers() {

        return passengerRepository.findAll();
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getAllPassengers'");
    }

    public Page<Passenger> getAllPassengers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return passengerRepository.findAll(pageable);
    }

    public Page<Train> getTrainsByPassengerId(Long passengerId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return trainRepository.findTrainsListByPassengerId(passengerId, pageable);
    }

    public Page<Address> findAddressByPassengerId(Long passengerId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return addressRepository.findAddressListByPassengerId(passengerId, pageable);
    }

}