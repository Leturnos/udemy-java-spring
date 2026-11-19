package com.udemy.spring_boot.repository;

import com.udemy.spring_boot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
