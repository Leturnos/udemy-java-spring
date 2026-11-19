package com.udemy.spring_boot.integrationtests.dto.Wrapper.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.udemy.spring_boot.integrationtests.dto.PersonDTO;

import java.io.Serializable;
import java.util.List;

public class PersonHalEmbedded implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("people") // dispensável, já que a variável tem o mesmo nome
    private List<PersonDTO> people;

    public PersonHalEmbedded() {}

    public List<PersonDTO> getPeople() {
        return people;
    }

    public void setPeople(List<PersonDTO> people) {
        this.people = people;
    }
}
