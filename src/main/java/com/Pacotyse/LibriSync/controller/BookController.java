package com.Pacotyse.LibriSync.controller;

import com.Pacotyse.LibriSync.model.Book;
import com.Pacotyse.LibriSync.repository.BookRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BookController {
    private final BookRepository repository;

    BookController(BookRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/books")
    CollectionModel<EntityModel<Book>> all() {

            List<EntityModel<Book>> books = repository.findAll().stream()
                    .map(book -> EntityModel.of(book,
                            linkTo(methodOn(BookController.class).all()).withRel("books")))
                    .collect(Collectors.toList());

            return CollectionModel.of(books, linkTo(methodOn(BookController.class).all()).withSelfRel());
    }
}
