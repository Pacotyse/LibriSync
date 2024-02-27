package com.Pacotyse.LibriSync.repository;

import com.Pacotyse.LibriSync.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
