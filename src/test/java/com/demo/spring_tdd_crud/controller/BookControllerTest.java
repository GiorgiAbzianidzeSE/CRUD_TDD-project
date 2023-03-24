package com.demo.spring_tdd_crud.controller;

import com.demo.spring_tdd_crud.model.Book;
import com.demo.spring_tdd_crud.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(BookController.class)
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void bookController_createBook_returnSavedBook() throws Exception{
        final Book book1 = Book.builder()
                .id(1L)
                .content("testContent1")
                .rating(-1)
                .name("testName1")
                .build();

        when(bookService.save(any(Book.class))).thenReturn(book1);

       mockMvc.perform(post("/api/books/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book1)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name" , CoreMatchers.is(book1.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content" , CoreMatchers.is(book1.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating" , CoreMatchers.is(book1.getRating())));



    }

    @Test
    void bookController_getAll_returnTwoBooks() throws Exception{
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

        when(bookService.findAll()).thenReturn(List.of(book1, book2));

        mockMvc.perform(get("/api/books/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()" , CoreMatchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", CoreMatchers.is(book1.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", CoreMatchers.is(book1.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(book1.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rating", CoreMatchers.is(book1.getRating())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].content", CoreMatchers.is(book2.getContent())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", CoreMatchers.is(book2.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", CoreMatchers.is(book2.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].rating", CoreMatchers.is(book2.getRating())));
    }

    @Test
    void bookController_getBookById_returnBookWithId1() throws Exception{
        final Book book1 = Book.builder()
                .id(1L)
                .content("testContent1")
                .rating(-1)
                .name("testName1")
                .build();

        when(bookService.findById(any(Long.class))).thenReturn(book1);


        mockMvc.perform(get("/api/books/{bookId}" , -1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id" , CoreMatchers.is(book1.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating" , CoreMatchers.is(book1.getRating())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name" , CoreMatchers.is(book1.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content" , CoreMatchers.is(book1.getContent())));


    }


}
