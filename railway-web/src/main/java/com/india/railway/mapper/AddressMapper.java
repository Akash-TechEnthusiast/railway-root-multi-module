package com.india.railway.mapper;
import com.india.railway.dto.AddressDTO;
import com.india.railway.model.mysql.Address;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressDTO dto);

   //AddressDTO toDto(Address entity);
}
