package com.Pacotyse.LibriSync.exception;

/**
 * Exception thrown when a book with a specific reference ID is not found.
 */
public class BookNotFoundException extends RuntimeException {

    /**
     * Constructs a new BookNotFoundException with the given book reference ID.
     * @param id The reference ID of the book.
     */
    public BookNotFoundException(Long id) {
        super("Book's reference " + id + " not found.");
    }
}
