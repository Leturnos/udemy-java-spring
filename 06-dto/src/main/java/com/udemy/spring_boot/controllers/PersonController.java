package com.udemy.spring_boot.controllers;

import com.udemy.spring_boot.data.dto.PersonDTO;
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

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PersonDTO> findAll() {
        return personServices.findAll();
    }

    @GetMapping(value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO findById(@PathVariable("id") Long id) {
        return personServices.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO create(@RequestBody PersonDTO person) { // sem o RequestBody, não conseguimos pegar os valores do body
        return personServices.create(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        personServices.delete(id);

        return ResponseEntity.noContent().build();
    }
}