package com.udemy.spring_boot.repository;

import com.udemy.spring_boot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying(clearAutomatically = true) // indica que a query altera dados e deve ser usada dentro de uma transação
    @Query("UPDATE Person p SET p.enabled = false WHERE p.id = :id") // forma que está no objeto java e não no collumn
    void disablePerson(@Param("id") Long id);
}
