package com.Pacotyse.LibriSync.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Represents a book in the library management system.
 */
@Entity
@Data
@Table(name = "book")
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
}
