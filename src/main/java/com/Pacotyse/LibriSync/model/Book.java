package com.Pacotyse.LibriSync.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a book in the library management system.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * The title of the book.
     */
    private @NotNull
    @Column(name = "title") String title;

    /**
     * The author of the book.
     */
    private @NotNull
    @Column(name = "author") String author;

    /**
     * Default constructor.
     */
    public Book() {}

    /**
     * Constructs a book with the given title and author.
     * @param title The title of the book.
     * @param author The author of the book.
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Gets the id of the book.
     * @return The id of the book.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the title of the book.
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     * @param title The new title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author of the book.
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     * @param author The new author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}
