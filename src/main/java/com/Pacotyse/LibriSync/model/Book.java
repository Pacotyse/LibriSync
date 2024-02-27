package com.Pacotyse.LibriSync.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;

    public Long getId() {
        return id;
    }
}
