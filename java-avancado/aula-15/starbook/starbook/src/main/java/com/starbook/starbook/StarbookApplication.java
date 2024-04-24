package com.starbook.starbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.github.javafaker.Faker;
import com.starbook.starbook.model.Author;
import com.starbook.starbook.model.Book;
import com.starbook.starbook.model.Publisher;
import com.starbook.starbook.model.User;
import com.starbook.starbook.service.AuthorServiceV1;
import com.starbook.starbook.service.BookServiceV1;
import com.starbook.starbook.service.PublisherServiceV1;
import com.starbook.starbook.service.UserServiceV1;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableCaching
@SpringBootApplication
public class StarbookApplication {

    @Autowired
    private AuthorServiceV1 authorService;

    @Autowired
    private BookServiceV1 bookService;

    @Autowired
    private PublisherServiceV1 publisherService;

    @Autowired
    private UserServiceV1 userService;

    public static void main(String[] args) {
        SpringApplication.run(StarbookApplication.class, args);
    }

    @PostConstruct
    public void init() {
        log.info("Application started...");

        Faker faker = new Faker();

        // Criando alguns autores
        for (int i = 0; i < 100; i++) {
            Author author = new Author();
            author.setName(faker.book().author());
            authorService.create(author);
        }

        // Criando alguns livros
        for (int i = 0; i < 500; i++) {
            Book book = new Book();
            book.setTitle(faker.book().title());
            book.setGenre(faker.book().genre());
            book.setSubgenre(faker.book().genre());
            book.setPublication_date(faker.date().birthday());
            book.setPage_count(faker.number().numberBetween(100, 1000));
            book.setStars(faker.number().numberBetween(1, 6));
            book.setReview(faker.lorem().paragraph());
            book.setCover(faker.internet().image());
            book.setPhysical(faker.bool().bool());
            bookService.create(book);
        }

        // Criando um Publisher
        for (int i = 0; i < 300; i++) {
            Publisher publisher = new Publisher();
            publisher.setName(faker.book().publisher());
            publisherService.create(publisher);
        }

        // Criando um conjunto de usuários
        for (int i = 0; i < 200; i++) {
            User user = new User();
            user.setLogin(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            userService.create(user);
        }
    }
}
