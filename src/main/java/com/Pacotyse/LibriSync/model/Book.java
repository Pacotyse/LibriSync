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
    private @NotNull
    @Column(name = "editor") String editor;

    /**
     * The stock quantity of the book.
     */
    private @NotNull
    @Column(name = "stock") int stock = 1;

    /**
     * Default constructor.
     */
    public Book() {}

    /**
     * Constructs a book with the specified details.
     * @param isbn The ISBN of the book.
     * @param title The title of the book.
     * @param author The author of the book.
     * @param editor The editor of the book.
     */
    public Book(Long isbn, String title, String author, String editor) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.editor = editor;
    }

    /**
     * Gets the ISBN of the book.
     * @return The ISBN of the book.
     */
    public Long getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     * @param isbn The new ISBN of the book.
     */
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

    /**
     * Gets the editor of the book.
     * @return The editor of the book.
     */
    public String getEditor() {
        return editor;
    }

    /**
     * Sets the editor of the book.
     * @param editor The new editor of the book.
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * Gets the stock quantity of the book.
     * @return The stock quantity of the book.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock quantity of the book.
     * @param stock The new stock quantity of the book.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
