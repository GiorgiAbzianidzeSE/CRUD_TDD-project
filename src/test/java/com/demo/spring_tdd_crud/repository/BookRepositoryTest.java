package com.demo.spring_tdd_crud.repository;

import com.demo.spring_tdd_crud.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collection;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Slf4j
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;


    @Test
    void bookRepository_save_returnsSavedBook(){
        //arrange
        final Book bookBuilder = Book.builder()
                .content("testContent1")
                .rating(-1)
                .name("testName1")
                .build();
        //act
        final Book savedBook = bookRepository.save(bookBuilder);
        //assert

        log.info(savedBook.toString());

        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getId()).isGreaterThan(0L);
    }

    @Test
    void bookRepository_findAll_returnsTwoBooks(){
        final Book bookBuilder2 = Book.builder()
                .content("testContent2")
                .rating(-2)
                .name("testName2")
                .build();

        final Book bookBuilder3 = Book.builder()
                .content("testContent3")
                .rating(-3)
                .name("testName3")
                .build();

        bookRepository.save(bookBuilder2);
        bookRepository.save(bookBuilder3);

        final Collection<Book> iterable = bookRepository.findAll();

        Assertions.assertThat(iterable).isNotNull();
        Assertions.assertThat(iterable.size()).isEqualTo(2);
    }


    @Test
    void bookRepository_findById_returnBookWithGivenId(){
        final Book bookBuilder4 = Book.builder()
                .content("testContent4")
                .rating(-4)
                .name("testName4")
                .build();

        bookRepository.save(bookBuilder4);
        final long lastIndex = this.bookRepository.findAll().size();
        final Optional<Book> bookInDB = bookRepository.findById(lastIndex);
        Assertions.assertThat(bookInDB).isNotEmpty();
    }

    @Test
    void bookRepository_findByName_returnNotNullBook(){
        final Book bookBuilder5 = Book.builder()
                .content("testContent5")
                .rating(-5)
                .name("testName5")
                .build();

        bookRepository.save(bookBuilder5);

        final Book bookInDB = bookRepository.findByName("testName5");

        Assertions.assertThat(bookInDB).isNotNull();
        Assertions.assertThat(bookInDB.getName()).isEqualTo("testName5");

    }

    @Test
    void bookRepository_updateById_returnBookWithUpdatedData(){
        final Book bookBuilder6 = Book.builder()
                .content("testContent6")
                .rating(-6)
                .name("testName6")
                .build();

        final String updatedContent = "testContent66";
        final int updatedRating = -66;
        final String updatedName = "testContent66";
        bookRepository.save(bookBuilder6);

        final Book bookInDB = bookRepository.findById(bookBuilder6.getId()).get();

        final Book updatedBook = bookRepository.save(
          Book.builder()
                  .id(bookInDB.getId())
                  .rating(updatedRating)
                  .content(updatedContent)
                  .name(updatedName)
                  .build()
        );

        final Book updatedBookInDB = bookRepository.findById(bookInDB.getId()).get();

        Assertions.assertThat(bookInDB).isNotNull();
        Assertions.assertThat(updatedBookInDB).isNotNull();
        Assertions.assertThat(updatedBookInDB.getName()).isEqualTo(updatedName);
        Assertions.assertThat(updatedBookInDB.getRating()).isEqualTo(updatedRating);
        Assertions.assertThat(updatedBookInDB.getContent()).isEqualTo(updatedContent);

    }

    @Test
     void bookRepository_deleteById_returnEmptyBook(){
        final Book bookBuilder7 = Book.builder()
                .content("testContent7")
                .rating(-7)
                .name("testName7")
                .build();

        final String updatedContent = "testContent66";
        final int updatedRating = -66;
        final String updatedName = "testContent66";

        bookRepository.save(bookBuilder7);

        bookRepository.deleteById(bookBuilder7.getId());

        final Optional<Book> emptyBook = bookRepository.findById(bookBuilder7.getId());

        Assertions.assertThat(emptyBook).isEmpty();
    }
}
