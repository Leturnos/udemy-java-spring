package com.udemy.spring_boot.integrationtests.dto.Wrapper.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.udemy.spring_boot.integrationtests.dto.BookDTO;

import java.io.Serializable;
import java.util.List;

public class BookHalEmbedded implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("bookDTOList")
    private List<BookDTO> books;

    public BookHalEmbedded() {}

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
