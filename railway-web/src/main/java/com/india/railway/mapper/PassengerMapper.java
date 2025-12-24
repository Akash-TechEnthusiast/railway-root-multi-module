package com.india.railway.mapper;

import com.india.railway.dto.AddressDTO;
import com.india.railway.dto.PassengerRequestDTO;
import com.india.railway.model.mysql.*;
import com.india.railway.repository.mysql.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@Mapper(componentModel = "spring", uses = AddressMapper.class)
@Component
public class PassengerMapper {



    /* ================= CREATE ================= */

   //
    //

    // audit fields (from Auditable)
    //@Mapping(target = "id", ignore = true)
    //@Mapping(target = "pnumber", ignore = true)
   // @Mapping(target = "cellno", source = "cellno")
   // @Mapping(target = "aadhar_no", source = "aadhar_no")
   /* @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")
    @Mapping(target = "trains", source = "trainIds", qualifiedByName = "mapTrains")
    Passenger toEntity(PassengerRequestDTO dto);*/


    //@Mapping(target = "id", ignore = true)
    //@Mapping(target = "pnumber", ignore = true)
   // @Mapping(target = "user", ignore = true)
   // @Mapping(target = "trains", ignore = true)

     public Passenger toEntity(PassengerRequestDTO dto) {

                Passenger passenger = new Passenger();
                // ===== BASIC FIELDS =====
               // passenger.setId(dto.getId());
                passenger.setName(dto.getName());
                passenger.setAge(dto.getAge());
                passenger.setAadhar_no(dto.getAadhar_no());
                passenger.setEmail(dto.getEmail());
                passenger.setCellno(dto.getCellno());
                passenger.setDob(dto.getDob());
                passenger.setPincode(dto.getPincode());
                passenger.setSalary(dto.getSalary());
                passenger.setGender(dto.getGender());

                // ===== USER (userId → User entity) =====
                //User user = userRepository.findById(dto.getUserId())
                // .orElseThrow(() -> new RuntimeException("User not found"));

                User user =new User();
                user.setId(dto.getUserId());

                passenger.setUser(user);

                // ===== ADDRESS LIST =====
                List<Address> addresses = new ArrayList<Address>();
                for (AddressDTO addressDTO : dto.getAddress()) {

                    Address address = new Address();
                   // address.setId(addressDTO.getId());
                    address.setStreet(addressDTO.getStreet());
                    address.setCity(addressDTO.getCity());
                    address.setState(addressDTO.getState());
                    address.setCountry(addressDTO.getCountry());

                    // 🔑 IMPORTANT: bidirectional mapping
                    //address.setPassenger(passenger);

                    addresses.add(address);
                }
                passenger.setAddress(addresses);

                // ===== TRAINS (trainIds → Train entities) =====
                if (dto.getTrainIds() != null && !dto.getTrainIds().isEmpty()) {
                    Set<Train> trains = new HashSet<>();
                    for (String trainId : dto.getTrainIds()) {

                        Train train = new Train();
                        train.setId(trainId);
                        trains.add(train);
                    }
                    passenger.setTrains(trains);
                }

                // ===== SAVE =====


         return passenger;
     }




    /* ================= UPDATE ================= */
/*
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    @Mapping(target = "pnumber", ignore = true)

    // audit fields
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)

    // name mismatches
    @Mapping(target = "aadhar_no", source = "aadharNo")
    @Mapping(target = "cellno", source = "cellNo")
    @Mapping(target = "address", source = "addresses")

    // relations
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUser")


    void updateEntity(PassengerRequestDTO dto, @MappingTarget Passenger entity);

*/
    /* ================= CUSTOM MAPPERS ================= */



    /*@Named("mapUser")
    default User mapUser(String userId) {
        if (userId == null) return null;
        User user = new User();
        user.setId(userId);
        return user;
    }

    @Named("mapTrains")
    default Set<Train> mapTrains(Set<String> trainIds) {
        if (trainIds == null) return null;
        return trainIds.stream().map(id -> {
            Train train = new Train();
            train.setId(id);
            return train;
        }).collect(Collectors.toSet());
    }*/

}
