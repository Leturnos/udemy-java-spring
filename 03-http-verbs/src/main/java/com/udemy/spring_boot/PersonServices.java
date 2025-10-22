package com.udemy.spring_boot;

import com.udemy.spring_boot.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person create(Person person) {
        logger.info("creating one person");
        return person;
    }

    public Person update(Person person) {
        logger.info("updating one person");
        return person;
    }

    public void delete(String id) {
        logger.info("deleting one person");
    }

    public List<Person> findAll() {
        logger.info("finding 10 persons");
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById(String id) {
        logger.info("finding one person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFistName("Leandro");
        person.setLastName("Pereira");
        person.setAdress("Itamonte - MG, Brasil");
        person.setGender("Masculino");
        return person;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFistName("First name: " + i);
        person.setLastName("Last name: " + i);
        person.setAdress("Adress: ");
        person.setGender("Gender: ");
        return person;
    }
}
