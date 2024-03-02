package com.Pacotyse.LibriSync.exception;

public class DuplicateException extends RuntimeException {
            public DuplicateException() {
                super("Entity already exists.");
            }
}
