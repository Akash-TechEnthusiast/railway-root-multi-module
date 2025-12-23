package com.india.railway.service.mysql;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import com.india.railway.exception.*;
import com.india.railway.utility.EntityValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.india.railway.model.mysql.Address;
import com.india.railway.model.mysql.Passenger;
import com.india.railway.model.mysql.Train;
import com.india.railway.model.mysql.TrainNameProjection;
import com.india.railway.repository.mysql.AddressRepository;
import com.india.railway.repository.mysql.PassengerRepository;
import com.india.railway.repository.mysql.TrainRepository;

import jakarta.transaction.Transactional;

@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    AutoCodeGeneratorService autoCodeGeneratorService;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    EntityValidation entityValidation;

    @Autowired
    ApiResponseFactory apiResponseFactory;

    @Autowired
    ExecutorService passengerExecutor;

    @Override
    public ResponseEntity<ApiResponse<Passenger>> addPassenger(Passenger passenger) throws IllegalAccessException {

        try {
            Passenger saved = (Passenger) passengerExecutor.submit(() -> {
                Instant start = Instant.now();

                /*if (passengerRepository.existsById(passenger.getId())) {
                    throw new PassengerAlreadyExistsException("Passenger already exists!!");
                }*/

                if (passenger.getTrains() == null || passenger.getTrains().isEmpty()) {
                    throw new EntityNotFoundException("Trains list could not be empty");
                }

                List<String> validationErrors = entityValidation.validate(passenger);
                if (!validationErrors.isEmpty()) {
                    return apiResponseFactory.validationError(validationErrors);
                }

                autoCodeGeneratorService.generateId(passenger);
                Passenger data = passengerRepository.save(passenger);


                Instant end = Instant.now();
                long executionMilliSeconds = Duration.between(start, end).toMillis();
                log.info("Started at: {}", start);
                log.info("Ended at: {}", end);
                log.info("Execution time for passenger save: {} milli seconds", executionMilliSeconds);

                return data;
            }).get();


            return apiResponseFactory.success(
                    saved,
                    "Passenger created successfully",
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to save passenger", e);
        }
    }


@Override
public ResponseEntity<ApiResponse<Passenger>> updatePassenger(Passenger passenger) {
    Passenger existingPassenger = passengerRepository.findById(passenger.getId()).orElse(null);
    if (existingPassenger == null)
        throw new NoSuchEmployeeExistsException("No Such Employee exists!!");
    else {
        existingPassenger.setName(passenger.getName());
        existingPassenger.setAddress(passenger.getAddress());
        Passenger updated = passengerRepository.save(existingPassenger);

        return apiResponseFactory.success(
                updated,
                "Passenger updated successfully",
                HttpStatus.CREATED
        );
    }
}

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