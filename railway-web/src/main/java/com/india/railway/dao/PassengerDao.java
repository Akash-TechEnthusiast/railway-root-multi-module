package com.india.railway.dao;


import com.india.railway.model.mysql.Passenger;
import com.india.railway.model.mysql.Train;
import com.india.railway.model.mysql.User;
import com.india.railway.repository.mysql.PassengerRepository;
import com.india.railway.repository.mysql.TrainRepository;
import com.india.railway.repository.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PassengerDao {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TrainRepository trainRepository;


    // it can handle onetoone mapping and manytoone mapping cascade
    // just passing user (one to one) and train object (many to many) with their ids
    // relations will be updated automatically
    @Transactional
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Transactional
    public void deletePassenger(String id) {
         passengerRepository.deleteById(id);
    }

    @Transactional
    public Passenger updatePassenger(Passenger passenger) {

        //passenger.getTrains().stream()
        List<String> trainIds = passenger.getTrains().stream()
                .map(Train::getId)
                .toList();
        Set<Train> trains=new HashSet<>(trainRepository.findAllById(trainIds));
        ///passenger.getTrains().clear();
        //passenger.setTrains(trains);

        return passengerRepository.save(passenger);
    }

}
