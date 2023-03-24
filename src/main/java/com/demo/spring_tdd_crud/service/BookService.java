package com.demo.spring_tdd_crud.service;


import com.demo.spring_tdd_crud.exceptions.BookNotFoundException;
import com.demo.spring_tdd_crud.model.Book;
import com.demo.spring_tdd_crud.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository
                .findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    public Book save(Book book) {
        return this.bookRepository.save(book);
    }
}
