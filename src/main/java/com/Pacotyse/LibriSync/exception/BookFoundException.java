package com.Pacotyse.LibriSync.exception;

public class BookFoundException extends RuntimeException {

        public BookFoundException(Long id) {
            super("Book's reference " + id + " already exists.");
        }
}
