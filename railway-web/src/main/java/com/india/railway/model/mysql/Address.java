package com.india.railway.model.mysql;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // getter and setter and tostring
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Passenger passenger;

    private String city;
    private String state;
    private String country;
    private String pincode;
    private String doorno;
    private String wardno;
    private String street;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (pincode == null) {
            if (other.pincode != null)
                return false;
        } else if (!pincode.equals(other.pincode))
            return false;
        if (doorno == null) {
            if (other.doorno != null)
                return false;
        } else if (!doorno.equals(other.doorno))
            return false;
        if (wardno == null) {
            if (other.wardno != null)
                return false;
        } else if (!wardno.equals(other.wardno))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
        result = prime * result + ((doorno == null) ? 0 : doorno.hashCode());
        result = prime * result + ((wardno == null) ? 0 : wardno.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        return result;
    }

}
