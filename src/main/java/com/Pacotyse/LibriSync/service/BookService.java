package com.Pacotyse.LibriSync.service;

import com.Pacotyse.LibriSync.exception.DuplicateException;
import com.Pacotyse.LibriSync.exception.NotFoundException;
import com.Pacotyse.LibriSync.model.Book;
import com.Pacotyse.LibriSync.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing books in the library system.
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Adds a new book to the library.
     * @param newBook The new book to add.
     */
    public void addBook(Book newBook) {
        Book existingBook = bookRepository.findByIsbn(newBook.getIsbn());
        if (existingBook != null) {
            throw new DuplicateException();
        }
        bookRepository.save(newBook);
    }

    /**
     * Retrieves all books in the library.
     * @return A list of all books.
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Retrieves details of a specific book.
     * @param id The ID of the book to retrieve.
     * @return The details of the book.
     * @throws NotFoundException if the book with the given ID is not found.
     */
    public Book getOneBook(Long id) {
        return bookRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    /**
     * Updates details of a specific book.
     * @param isbn The ISBN of the book to update.
     * @param updatedBook The updated details of the book.
     * @return The updated book.
     * @throws NotFoundException if the book with the given ID is not found.
     */
    public Book updateBook(Long isbn, Book updatedBook) {
        Book existingBook = bookRepository.findById(isbn).orElseThrow(NotFoundException::new);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setEditor(updatedBook.getEditor());
        return bookRepository.save(existingBook);
    }

    /**
     * Deletes a specific book from the library.
     * @param isbn The ISBN of the book to delete.
     */
    public void deleteBook(Long isbn) {
        bookRepository.deleteById(isbn);
    }
}
