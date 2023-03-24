package com.demo.spring_tdd_crud.service;


import com.demo.spring_tdd_crud.exceptions.BookNotFoundException;
import com.demo.spring_tdd_crud.model.Book;
import com.demo.spring_tdd_crud.repository.BookRepository;
import com.demo.spring_tdd_crud.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void bookService_save_returnSavedBook(){
        final Book book1 = Book.builder()
                .id(1L)
                .content("testContent1")
                .rating(-1)
                .name("testName1")
                .build();

        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book1);

        final Book savedBook = bookService.save(book1);

        Assertions.assertThat(savedBook).isNotNull();

    }

    @Test
    void bookService_findAll_returnTwoBooks(){
        final Book book1 = Book.builder()
                .id(1L)
                .content("testContent1")
                .rating(-1)
                .name("testName1")
                .build();

        final Book book2 = Book.builder()
                .id(2L)
                .content("testContent2")
                .rating(-2)
                .name("testName2")
                .build();

        when(bookRepository.findAll()).thenReturn(List.of(book1 , book2));

        final Collection<Book> books = bookService.findAll();

        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.size()).isEqualTo(2);
    }

    @Test
    void bookService_findById_throwsBookNotFoundException_whenBookIsNotFound(){
        final Book book1 = Book.builder()
                .id(1L)
                .content("testContent1")
                .rating(-1)
                .name("testName1")
                .build();

        when(bookRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrowsExactly(BookNotFoundException.class , () -> {
           bookService.findById(-1L);
        });
    }


    @Test
    void bookService_findById_returnBookOnId_whenBookIsFound(){
        final Book book1 = Book.builder()
                .id(1L)
                .content("testContent1")
                .rating(-1)
                .name("testName1")
                .build();

        when(bookRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(book1));

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> bookService.findById(-1L));
        Assertions.assertThat(bookService.findById(-1L)).isEqualTo(book1);
    }





}
