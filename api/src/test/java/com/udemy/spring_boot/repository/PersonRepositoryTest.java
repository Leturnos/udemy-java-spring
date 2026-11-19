package com.udemy.spring_boot.repository;

import com.udemy.spring_boot.integrationtests.testcontainer.AbstractIntegrationTest;
import com.udemy.spring_boot.model.Person;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    PersonRepository personRepository;
    private static Person person;

    @Test
    @Order(1)
    void findPeopleByName() {
        Pageable pageable = PageRequest.of(
                0, 12, Sort.by(Sort.Direction.ASC, "firstName"));

        person = personRepository
                .findPeopleByName("Leandro", pageable)
                .getContent()
                .get(0);

        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals("Leandro", person.getFirstName());
        assertEquals("Pereira", person.getLastName());
        assertEquals("Moradas do Bosque", person.getAddress());
        assertEquals("Male", person.getGender());
        assertTrue(person.getEnabled());
    }

    @Test
    @Order(2)
    void disablePerson() {

        Pageable pageable = PageRequest.of(
                0, 12, Sort.by(Sort.Direction.ASC, "firstName"));

        Person person = personRepository
                .findPeopleByName("Leandro", pageable)
                .getContent()
                .get(0);

        Long id = person.getId();
        personRepository.disablePerson(id);

        person = personRepository.findById(id).get();

        assertFalse(person.getEnabled());
    }
}