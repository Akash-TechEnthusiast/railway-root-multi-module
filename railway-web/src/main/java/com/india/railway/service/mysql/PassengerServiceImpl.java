package com.india.railway.service.mysql;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import com.india.railway.dao.PassengerDao;
import com.india.railway.dto.PassengerRequestDTO;
import com.india.railway.exception.*;
import com.india.railway.mapper.PassengerMapper;
import com.india.railway.model.mysql.*;
import com.india.railway.utility.EntityValidation;
import com.india.railway.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    @Autowired
    PassengerMapper passengerMapper;

    @Autowired
    PassengerDao passengerDao;

    @Override
    public ResponseEntity<ApiResponse<Passenger>> addPassenger(PassengerRequestDTO dto) throws IllegalAccessException {

        try {
            Passenger response= (Passenger) passengerExecutor.submit(() -> {
                long currentTimeMillis = System.currentTimeMillis();

                /*if (passengerRepository.existsById(passenger.getId())) {
                    throw new PassengerAlreadyExistsException("Passenger already exists!!");
                }*/

                if (dto.getTrainIds()== null || dto.getTrainIds().isEmpty()) {
                    throw new EntityNotFoundException("Trains list could not be empty");
                }
                    // passengerMapper.toEntity(dto);
                Passenger passenger = passengerMapper.toEntity(dto);

                List<String> validationErrors = entityValidation.validate(dto);
                if (!validationErrors.isEmpty()) {
                    return apiResponseFactory.validationError(validationErrors);
                }
                // skipping the auto generation fields for onetoone and manytomany relationships
                // those will come as dropdowns from the client side
                autoCodeGeneratorService.generateId(passenger);

                Passenger data= passengerDao.savePassenger(passenger);

                Utility.executionTime(currentTimeMillis,"passenger save");

                return data;
            }).get();

            //Passenger saved =new Passenger();

            return apiResponseFactory.success(
                    response,
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
public Optional<Passenger> getPassenger(String id) {
    // TODO Auto-generated method stub

    Optional<Passenger> single = passengerRepository.findById(id);
    String value=single.get().getUser().getId();
    //String username=single.get().getUser().getUsername();
    //String text=single.get().getTrains().iterator().next().getTrain_name();

    /*Passenger passenger = passengerRepository.findPassengerWithTrains(id)
            .orElseThrow(() -> new RuntimeException("Passenger not found"));

    List<TrainNameProjection> traisnList = trainRepository.findTrainsByPassengerId(id);

    Set<Train> trains = new HashSet<>();
    for (TrainNameProjection train : traisnList) {
        Train t = new Train();
        t.setTrain_name(train.getTrainName());
        t.setTrain_number(train.getTrainNumber());
        trains.add(t);

    }
    passenger.setTrains(trains);*/

    return single;

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