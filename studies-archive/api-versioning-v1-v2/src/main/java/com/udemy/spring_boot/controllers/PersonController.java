package com.udemy.spring_boot.controllers;

import com.udemy.spring_boot.data.dto.v1.PersonDTOV1;
import com.udemy.spring_boot.data.dto.v2.PersonDTOV2;
import com.udemy.spring_boot.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired // para não precisa do "= new PersonServices();"
    private PersonServices personServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTOV1> findAll() {
        return personServices.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTOV1 findById(@PathVariable("id") Long id) {
        return personServices.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTOV1 create(@RequestBody PersonDTOV1 person) { // sem o RequestBody, não conseguimos pegar os valores do body
        return personServices.create(person);
    }

    @PostMapping(value = "/v2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTOV2 create(@RequestBody PersonDTOV2 person) {
        return personServices.createV2(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTOV1 update(@RequestBody PersonDTOV1 person) {
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personServices.delete(id);

        return ResponseEntity.noContent().build();
    }
}