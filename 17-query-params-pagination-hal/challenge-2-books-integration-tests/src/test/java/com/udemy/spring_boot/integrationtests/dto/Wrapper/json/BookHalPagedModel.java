package com.udemy.spring_boot.integrationtests.dto.Wrapper.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BookHalPagedModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("_embedded")
    private BookHalEmbedded embeddedDTO;

    public BookHalPagedModel() {}

    public BookHalEmbedded getEmbeddedDTO() {
        return embeddedDTO;
    }

    public void setEmbeddedDTO(BookHalEmbedded embeddedDTO) {
        this.embeddedDTO = embeddedDTO;
    }
}
