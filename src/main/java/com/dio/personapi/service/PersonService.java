package com.dio.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.personapi.dto.mapper.PersonMapper;
import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.dto.response.MessageResponseDTO;
import com.dio.personapi.entity.Person;
import com.dio.personapi.exception.PersonNotFoundException;
import com.dio.personapi.repository.PersonRepository;

@Service
public class PersonService {
	
	private PersonRepository personRepository;
	
	private PersonMapper personMapper = PersonMapper.INSTANCE;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person personToSave = PersonMapper.INSTANCE.toModel(personDTO);
		
		Person savedPerson = personRepository.save(personToSave);
		return MessageResponseDTO
				.builder()
				.message("Created Person with ID: " + savedPerson.getId())
				.build();
	}
	
	public List<PersonDTO> listAll() {
        List<Person> people = personRepository.findAll();
//        System.out.println(people);
//        System.out.println(people.stream());
//        System.out.println(people.stream().map(personMapper::toDTO));
//        System.out.println(people.stream().map(personMapper::toDTO).collect(Collectors.toList()));
        return people.stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

	public PersonDTO findById(Long id) throws PersonNotFoundException{
		 Person person = personRepository.findById(id)
	                .orElseThrow(() -> new PersonNotFoundException(id));

	        return personMapper.toDTO(person);
	}

	public MessageResponseDTO update(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        Person updatedPerson = PersonMapper.INSTANCE.toModel(personDTO);
        Person savedPerson = personRepository.save(updatedPerson);

        MessageResponseDTO messageResponse = createMessageResponse("Person successfully updated with ID ", savedPerson.getId());

        return messageResponse;
    }
	
	
	
	private MessageResponseDTO createMessageResponse(String s, Long id2) {
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }
}
