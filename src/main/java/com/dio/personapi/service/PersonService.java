package com.dio.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.personapi.dto.mapper.PersonMapper;
import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.dto.response.MessageResponseDTO;
import com.dio.personapi.entity.Person;
import com.dio.personapi.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	private PersonMapper personMapper;
	
	
	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person personToSave = PersonMapper.INSTANCE.toModel(personDTO);
		
		Person savedPerson = personRepository.save(personToSave);
		return MessageResponseDTO
				.builder()
				.message("Created Person with ID: " + savedPerson.getId())
				.build();
	}
}
