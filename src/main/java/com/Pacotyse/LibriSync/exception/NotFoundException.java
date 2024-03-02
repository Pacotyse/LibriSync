package com.Pacotyse.LibriSync.exception;

public class NotFoundException extends RuntimeException {

        public NotFoundException() {
            super("Entity not found.");
        }

}
