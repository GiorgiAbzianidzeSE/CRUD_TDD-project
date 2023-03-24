package com.demo.spring_tdd_crud.controller;

import com.demo.spring_tdd_crud.model.Book;
import com.demo.spring_tdd_crud.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;


    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks(){
        return this.bookService.findAll();
    }

    @GetMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable("bookId") final Long id){
        return this.bookService
                 .findById(id);
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody final Book book){
        return this.bookService.save(book);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable("bookId") final Long id,
                    @RequestBody final Book book){
        final Book bookInDB = getBookById(id);
        final Book newBook = Book.builder()
                .content(book.getContent())
                .name(book.getName())
                .rating(book.getRating())
                .id(bookInDB.getId())
                .build();
        return this.bookService.save(newBook);
    }


}
