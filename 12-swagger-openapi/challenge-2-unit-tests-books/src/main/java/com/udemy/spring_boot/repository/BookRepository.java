package com.udemy.spring_boot.repository;

import com.udemy.spring_boot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
