package com.demo.spring_tdd_crud.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Delegate;

@Entity
@Builder
@Getter
@EqualsAndHashCode
@ToString
@Table(name = "Book_table")
public final class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_content")
    private String content;

    @Column(name = "book_rating")
    private Integer rating;

    protected Book(){

    }

    private Book(Long id , String name , String content , Integer rating){
        this.name = name;
        this.content = content;
        this.rating = rating;
        this.id = id;
    }
}
