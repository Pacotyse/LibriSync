package com.Pacotyse.LibriSync.controller;

import com.Pacotyse.LibriSync.exception.BookFoundException;
import com.Pacotyse.LibriSync.exception.NotFoundException;
import com.Pacotyse.LibriSync.model.Book;
import com.Pacotyse.LibriSync.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controller class for handling requests related to books.
 */
@RestController
public class BookController {

    private final BookRepository repository;

    /**
     * Constructs a new BookController with the specified BookRepository.
     * @param repository The BookRepository to be used.
     */
    BookController(BookRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all books and returns them as a collection model with HATEOAS links.
     * @return A collection model containing all books with HATEOAS links.
     */
    @GetMapping("/books")
    CollectionModel<EntityModel<Book>> all() {
        List<EntityModel<Book>> books = repository.findAll().stream()
                .map(book -> EntityModel.of(book,
                        linkTo(methodOn(BookController.class).one(book.getIsbn())).withSelfRel(),
                        linkTo(methodOn(BookController.class).all()).withRel("books")))
                .collect(Collectors.toList());

        return CollectionModel.of(books, linkTo(methodOn(BookController.class).all()).withSelfRel());
    }

    /**
     * Saves a new book.
     *
     * @param newBook The book to be saved. It should be passed in the request body.
     * @return The saved book.
     */
    @PostMapping("/books")
    public Book newBook(@Valid @RequestBody Book newBook) {
        if (repository.existsById(newBook.getIsbn())) {
            throw new BookFoundException(newBook.getIsbn());
        } else {
            return repository.save(newBook);
        }
    }

    /**
     * Retrieves a single book by its id and returns it as an entity model with HATEOAS links.
     * @param id The id of the book to be retrieved.
     * @return The book with the specified id as an entity model with HATEOAS links.
     */
    @GetMapping("/books/{id}")
    EntityModel<Book> one(@PathVariable Long id) {
        Book book = repository.findById(id)
                .orElseThrow(NotFoundException::new);

        return EntityModel.of(book,
                linkTo(methodOn(BookController.class).one(id)).withSelfRel(),
                linkTo(methodOn(BookController.class).all()).withRel("books"));

    }

    /**
     * Replaces the book with the specified ID with the provided new book details.
     *
     * @param newBook The new details of the book.
     * @param id      The ID of the book to be replaced.
     * @return The updated book.
     */
    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable Long id) {
        return repository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setEditor(newBook.getEditor());
                    book.setStock(newBook.getStock());
                    return repository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setIsbn(id);
                    return repository.save(newBook);
                });
    }

    /**
     * Deletes a book with the specified ID.
     *
     * @param id The ID of the book to be deleted.
     */
    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
