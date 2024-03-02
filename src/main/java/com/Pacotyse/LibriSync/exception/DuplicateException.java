package com.Pacotyse.LibriSync.exception;

public class DuplicateException extends RuntimeException {
            public DuplicateException() {
                super("An account with the email already exists.");
            }
}
