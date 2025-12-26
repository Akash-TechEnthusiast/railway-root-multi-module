package com.india.railway.mapper;

import com.india.railway.dto.AddressDTO;
import com.india.railway.dto.PassengerRequestDTO;
import com.india.railway.dto.TrainDTO;
import com.india.railway.dto.UserDTO;
import com.india.railway.model.mysql.*;
import com.india.railway.repository.mysql.TrainRepository;
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

    @Autowired
    TrainRepository trainRepository;

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

public Passenger updateEntity(PassengerRequestDTO dto, Passenger passenger) {

    // 1️⃣ Load EXISTING Passenger (MANDATORY)

    // 2️⃣ Load EXISTING User (MANDATORY)
    if (dto.getUserId() != null) {
        User user = new User();
        user.setId(dto.getUserId());
        passenger.setUser(user); // managed entity → SAFE
    }

    // 3️⃣ Update simple fields
    passenger.setName(dto.getName()!=null?dto.getName():passenger.getName());
    passenger.setAge(dto.getAge()!=null?dto.getAge():passenger.getAge());
    passenger.setAadhar_no(dto.getAadhar_no()!=null?dto.getAadhar_no():passenger.getAadhar_no());
    passenger.setEmail(dto.getEmail()!=null?dto.getEmail():passenger.getEmail());
    passenger.setCellno(dto.getCellno()!=null?dto.getCellno():passenger.getCellno());
    passenger.setDob(dto.getDob()!=null?dto.getDob():passenger.getDob());
    passenger.setPincode(dto.getPincode()!=null?dto.getPincode():passenger.getPincode());
    passenger.setSalary(dto.getSalary()!=null?dto.getSalary():passenger.getSalary());
    passenger.setGender(dto.getGender()!=null?dto.getGender():passenger.getGender());
    passenger.setCreatedDate(null);
    passenger.setLastModifiedDate(null);

    // 4️⃣ Update Address (replace old ones)
    if (dto.getAddress() != null) {
        List<Address> addresses = dto.getAddress().stream().map(a -> {
            Address address = new Address();
            address.setId(a.getId()!=null?a.getId():null); // important for UPDATE
            address.setStreet(a.getStreet()!=null?a.getStreet():address.getStreet());
            address.setCity(a.getCity()!=null?a.getCity():address.getCity());
            address.setState(a.getState()!=null?a.getState():address.getState());
            address.setCountry(a.getCountry()!=null?a.getCountry():address.getCountry());
            //address.setPassenger(passenger); // owning side
            return address;
        }).toList();

        passenger.setAddress(addresses);
    }

    // 5️⃣ Update Trains (IDs only)
    if (dto.getTrainIds() != null) {
        Set<Train> trains = dto.getTrainIds().stream().map(id -> {
            Train train = new Train();
            train.setId(id);
            return train;
        }).collect(Collectors.toSet());

        passenger.setTrains(trains);
    }

    // 6️⃣ Save (UPDATE happens)
    return passenger;
}


    public PassengerRequestDTO toDto(Passenger passenger) {

        PassengerRequestDTO dto = new PassengerRequestDTO();

        // ===== BASIC FIELDS =====
        dto.setId(passenger.getId());
        dto.setName(passenger.getName());
        dto.setAge(passenger.getAge());
        dto.setAadhar_no(passenger.getAadhar_no());
        dto.setEmail(passenger.getEmail());
        dto.setCellno(passenger.getCellno());
        dto.setDob(passenger.getDob());
        dto.setPincode(passenger.getPincode());
        dto.setSalary(passenger.getSalary());
        dto.setGender(passenger.getGender());

        // ===== USER =====
        if (passenger.getUser() != null) {
            //dto.setUserId(passenger.getUser().getId());
            UserDTO userDTO = new UserDTO();
            userDTO.setId(passenger.getUser().getId());
            userDTO.setUsername(passenger.getUser().getUsername());
            userDTO.setUserNumber(passenger.getUser().getUserNumber());
            dto.setUser(userDTO);
        }

        // ===== ADDRESS LIST =====
        if (passenger.getAddress() != null) {
            List<AddressDTO> addressDTOs = new ArrayList<>();

            for (Address address : passenger.getAddress()) {
                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setId(address.getId());
                addressDTO.setStreet(address.getStreet());
                addressDTO.setCity(address.getCity());
                addressDTO.setState(address.getState());
                addressDTO.setCountry(address.getCountry());

                addressDTOs.add(addressDTO);
            }
            dto.setAddress(addressDTOs);
        }

        // ===== TRAINS =====
        if (passenger.getTrains() != null) {
            //Set<String> trainIds = new HashSet<>();
            Set<TrainDTO> trains = new HashSet<>();

            for (Train train : passenger.getTrains()) {
                TrainDTO trainDTO = new TrainDTO();
                trainDTO.setId(train.getId());
                trainDTO.setTrain_name(train.getTrain_name());
                trainDTO.setTrain_number(train.getTrain_number());
                trains.add(trainDTO);
               // trainIds.add(train.getId());
            }
            //dto.setTrainIds(trainIds);
            dto.setTrains(trains);
        }

        return dto;
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
