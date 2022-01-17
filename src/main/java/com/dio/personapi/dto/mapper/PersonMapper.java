package com.dio.personapi.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.entity.Person;

@Mapper(componentModel="spring")
public interface PersonMapper {
	
	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
	
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);
    
    
    PersonDTO toDTO(Person person);
}
