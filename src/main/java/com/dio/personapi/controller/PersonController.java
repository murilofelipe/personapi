package com.dio.personapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.personapi.dto.response.MessageResponseDTO;
import com.dio.personapi.entity.Person;
import com.dio.personapi.repository.PersonRepository;

@RestController
@RequestMapping("api/v1/people")
public class PersonController {
	
	private PersonRepository personRespository;
	
	@Autowired
	public PersonController(PersonRepository personRepository) {
		this.personRespository = personRepository;
	}
	
	@PostMapping
	public MessageResponseDTO createPerson(@RequestBody Person person) {
		Person savedPerson = personRespository.save(person);
		return MessageResponseDTO
				.builder()
				.message("Created Person with ID: " + savedPerson.getId())
				.build();
	}
	
	@GetMapping
	public String getBook() {
		return "API Test";
	}

}
