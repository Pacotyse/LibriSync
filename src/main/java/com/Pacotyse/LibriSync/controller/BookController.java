package com.Pacotyse.LibriSync.controller;

import com.Pacotyse.LibriSync.model.Book;
import com.Pacotyse.LibriSync.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controller class for managing books in the library system.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Adds a new book to the library.
     * @param newBook The new book to add.
     * @return The added book.
     */
    @PostMapping("/add")
    ResponseEntity<Book> addBook(@Valid @RequestBody Book newBook) {
        bookService.addBook(newBook);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    /**
     * Retrieves all books in the library.
     * @return A collection of book resources.
     */
    @GetMapping("")
    CollectionModel<EntityModel<Book>> getAllBooks() {
        List<EntityModel<Book>> books = bookService.getAllBooks().stream()
                .map(book -> EntityModel.of(book,
                        linkTo(methodOn(BookController.class).getOneBook(book.getIsbn())).withSelfRel(),
                        linkTo(methodOn(BookController.class).getAllBooks()).withRel("books")))
                .collect(Collectors.toList());

        return CollectionModel.of(books, linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel());
    }

    /**
     * Retrieves details of a specific book.
     * @param isbn The ISBN of the book to retrieve.
     * @return The details of the book.
     */
    @GetMapping("/{isbn}")
    EntityModel<Book> getOneBook(@PathVariable Long isbn) {
        return EntityModel.of(bookService.getOneBook(isbn),
                linkTo(methodOn(BookController.class).getOneBook(isbn)).withSelfRel(),
                linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
    }

    /**
     * Updates details of a specific book.
     * @param isbn The ISBN of the book to update.
     * @param updatedBook The updated details of the book.
     * @return The updated book.
     */
    @PutMapping("/{isbn}")
    ResponseEntity<Book> updateBook(@PathVariable Long isbn, @Valid @RequestBody Book updatedBook) {
        Book book = bookService.updateBook(isbn, updatedBook);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     * Deletes a specific book from the library.
     * @param isbn The ISBN of the book to delete.
     * @return A response entity indicating the success of the operation.
     */
    @DeleteMapping("/{isbn}")
    ResponseEntity<Book> deleteBook(@PathVariable Long isbn) {
        bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
