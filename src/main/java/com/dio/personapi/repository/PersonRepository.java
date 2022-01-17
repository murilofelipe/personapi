package com.dio.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dio.personapi.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}