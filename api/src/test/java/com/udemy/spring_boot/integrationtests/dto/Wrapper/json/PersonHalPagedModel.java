package com.udemy.spring_boot.integrationtests.dto.Wrapper.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PersonHalPagedModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("_embedded")
    private PersonHalEmbedded embeddedDTO;

    public PersonHalPagedModel() {}

    public PersonHalEmbedded getEmbeddedDTO() {
        return embeddedDTO;
    }

    public void setEmbeddedDTO(PersonHalEmbedded embeddedDTO) {
        this.embeddedDTO = embeddedDTO;
    }
}
