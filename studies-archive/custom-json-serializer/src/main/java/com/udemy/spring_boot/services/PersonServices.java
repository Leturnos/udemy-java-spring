package com.udemy.spring_boot.services;

import com.udemy.spring_boot.data.dto.PersonDTO;
import com.udemy.spring_boot.exception.ResourceNotFoundException;
import static com.udemy.spring_boot.mapper.ObjectMapper.parseObject;
import static com.udemy.spring_boot.mapper.ObjectMapper.parseListObjects;
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

    public PersonDTO create(PersonDTO person) {
        logger.info("creating one person");

        var entity = parseObject(person, Person.class);
        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("updating one person");
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        logger.info("finding all persons");
        return parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("finding one person");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return parseObject(entity, PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("deleting one person");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        personRepository.delete(entity);
    }
}
