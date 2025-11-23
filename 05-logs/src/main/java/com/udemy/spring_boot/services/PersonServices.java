package com.udemy.spring_boot.services;

import com.udemy.spring_boot.exception.ResourceNotFoundException;
import com.udemy.spring_boot.model.Person;
import com.udemy.spring_boot.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    public Person create(Person person) {
        logger.info("creating one person");
        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("updating one person");
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFistName(person.getFistName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());
        return personRepository.save((entity));
    }

    public List<Person> findAll() {
        logger.info("finding all persons");
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        logger.info("finding one person");

        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

    public void delete(Long id) {
        logger.info("deleting one person");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        personRepository.delete(entity);
    }
}
