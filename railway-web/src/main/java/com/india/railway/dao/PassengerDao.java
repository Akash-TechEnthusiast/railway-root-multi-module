package com.india.railway.dao;


import com.india.railway.model.mysql.Passenger;
import com.india.railway.model.mysql.User;
import com.india.railway.repository.mysql.PassengerRepository;
import com.india.railway.repository.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassengerDao {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    UserRepository userRepository;



    @Transactional
    public Passenger savePassenger(Passenger passenger) {
        // 1️⃣ Create new Passenger (TRANSIENT)
       /* if(passenger.getUser()!=null && passenger.getUser().getId()!=null){
            User user = userRepository.findById(passenger.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            passenger.setUser(user);
        }*/
        //User user = userRepository.getReferenceById(passenger.getUser().getId());
        // 3️⃣ Save Passenger ONLY
        return passengerRepository.save(passenger);
    }
}
