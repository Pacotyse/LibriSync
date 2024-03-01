package com.Pacotyse.LibriSync.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a book in the library management system.
 */
@Entity
public class Book {

    @Id
    private @NotNull
    @Column(name = "isbn") Long isbn;

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
     * The editor of the book.
     */
    private
    @Column(name = "editor") String editor;

    /**
     * Default constructor.
     */
    public Book() {}

    public Book(Long isbn, String title, String author, String editor) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.editor = editor;
    }

    /**
     * Gets the id of the book.
     * @return The id of the book.
     */
    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
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

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
}
