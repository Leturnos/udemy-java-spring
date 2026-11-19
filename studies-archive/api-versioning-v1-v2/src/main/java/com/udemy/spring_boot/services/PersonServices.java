package com.udemy.spring_boot.services;

import com.udemy.spring_boot.data.dto.v1.PersonDTOV1;
import com.udemy.spring_boot.data.dto.v2.PersonDTOV2;
import com.udemy.spring_boot.exception.ResourceNotFoundException;
import static com.udemy.spring_boot.mapper.ObjectMapper.parseObject;
import static com.udemy.spring_boot.mapper.ObjectMapper.parseListObjects;
import com.udemy.spring_boot.model.Person;
import com.udemy.spring_boot.model.custom.PersonMapper;
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

    @Autowired
    PersonMapper converter;

    public PersonDTOV1 create(PersonDTOV1 person) {
        logger.info("creating one person");

        var entity = parseObject(person, Person.class);
        return parseObject(personRepository.save(entity), PersonDTOV1.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("creating one person V2");

        var entity = converter.convertDTOToEntity(person);
        return converter.convertEntityToDTO(personRepository.save(entity));
    }

    public PersonDTOV1 update(PersonDTOV1 person) {
        logger.info("updating one person");
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return parseObject(personRepository.save(entity), PersonDTOV1.class);
    }

    public List<PersonDTOV1> findAll() {
        logger.info("finding all persons");
        return parseListObjects(personRepository.findAll(), PersonDTOV1.class);
    }

    public PersonDTOV1 findById(Long id) {
        logger.info("finding one person");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return parseObject(entity, PersonDTOV1.class);
    }

    public void delete(Long id) {
        logger.info("deleting one person");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        personRepository.delete(entity);
    }
}
