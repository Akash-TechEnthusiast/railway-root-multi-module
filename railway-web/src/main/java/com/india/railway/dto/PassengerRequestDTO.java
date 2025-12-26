package com.india.railway.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PassengerRequestDTO {

    // ✅ Required only for UPDATE
    private String id;

    @NotNull(message = "name must not be null")
    @Size(min = 5, max = 15, message = "Name should be at least 5 and at most 15 characters")
    private String name;

    @NotNull
    @Min(value = 18, message = "Age must be >= 18")
    private Integer age;

    @NotNull(message = "Aadhar number cannot be null")
    @Pattern(regexp = "\\d{12}", message = "Aadhar number must be exactly 12 digits")
    private String aadhar_no;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Cell number cannot be null")
    @Pattern(regexp = "\\+91[0-9]{10}", message = "Phone must start with +91 followed by 10 digits")
    private String cellno;

    @NotNull
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotNull(message = "Pincode cannot be null")
    @Pattern(regexp = "\\d{6}", message = "Pincode must be exactly 6 digits")
    private String pincode;

    @NotNull
    @DecimalMin(value = "100.00", message = "Salary must be at least 100")
    @DecimalMax(value = "100000.00", message = "Salary cannot exceed 100000")
    private Long salary;

    private String gender;

    // 👇 Only ID needed in DTO
    @NotNull(message = "userId must not be null")
    private String userId;

    private UserDTO user;


    @Valid
    @Size(min = 1, message = "Passenger must have at least one address")
    private List<AddressDTO> address;

    private Set<String> trainIds;

    private Set<TrainDTO> trains;

    public Set<TrainDTO> getTrains() {
        return trains;
    }

    public void setTrains(Set<TrainDTO> trains) {
        this.trains = trains;
    }
// getters & setters


    public String getAadhar_no() {
        return aadhar_no;
    }

    public void setAadhar_no(String aadhar_no) {
        this.aadhar_no = aadhar_no;
    }

    public String getCellno() {
        return cellno;
    }

    public void setCellno(String cellno) {
        this.cellno = cellno;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }


    public String getEmail() {
        return email;
    }



    public String getPincode() {
        return pincode;
    }

    public Long getSalary() {
        return salary;
    }

    public String getGender() {
        return gender;
    }

    public String getUserId() {
        return userId;
    }

    public List<AddressDTO> getAddress() {
        return address;
    }

    public Set<String> getTrainIds() {
        return trainIds;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAddress(List<AddressDTO> address) {
        this.address = address;
    }

    public void setTrainIds(Set<String> trainIds) {
        this.trainIds = trainIds;
    }
}
