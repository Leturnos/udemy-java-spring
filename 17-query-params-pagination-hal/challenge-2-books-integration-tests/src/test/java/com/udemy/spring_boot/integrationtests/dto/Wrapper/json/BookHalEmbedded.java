package com.udemy.spring_boot.integrationtests.dto.Wrapper.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.udemy.spring_boot.integrationtests.dto.BookDTO;

import java.io.Serializable;
import java.util.List;

public class BookHalEmbedded implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("books") // dispensável, já que a variável tem o mesmo nome
    private List<BookDTO> book;

    public BookHalEmbedded() {}

    public List<BookDTO> getBook() {
        return book;
    }

    public void setBook(List<BookDTO> book) {
        this.book = book;
    }
}
