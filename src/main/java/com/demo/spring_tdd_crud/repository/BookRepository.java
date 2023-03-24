package com.demo.spring_tdd_crud.repository;

import com.demo.spring_tdd_crud.model.Book;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book , Long> {

    Book findByName(final String name);
}
