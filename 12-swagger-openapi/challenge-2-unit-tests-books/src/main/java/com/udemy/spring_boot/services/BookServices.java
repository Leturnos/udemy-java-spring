package com.udemy.spring_boot.services;

import com.udemy.spring_boot.controllers.BookController;
import com.udemy.spring_boot.data.dto.BookDTO;
import com.udemy.spring_boot.exception.RequiredObjectIsNullException;
import com.udemy.spring_boot.exception.ResourceNotFoundException;
import com.udemy.spring_boot.model.Book;
import com.udemy.spring_boot.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.udemy.spring_boot.mapper.ObjectMapper.parseListObjects;
import static com.udemy.spring_boot.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookServices {

    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository bookRepository;

    public BookDTO create(BookDTO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("creating one book");

        var entity = parseObject(book, Book.class);
        var dto = parseObject(bookRepository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public List<BookDTO> findAll() {
        logger.info("finding all books");
        var books = parseListObjects(bookRepository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks);
        return books;
    }

    public BookDTO findById(Long id) {
        logger.info("finding one book");

        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("updating one book");
        Book entity = bookRepository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        var dto = parseObject(bookRepository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("deleting one book");

        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        bookRepository.delete(entity);
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
