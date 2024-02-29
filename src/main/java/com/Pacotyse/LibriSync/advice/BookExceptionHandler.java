package com.Pacotyse.LibriSync.advice;

import com.Pacotyse.LibriSync.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Advice class to handle exceptions related to Book entities.
 */
@ControllerAdvice
public class BookExceptionHandler {

    /**
     * Handles BookNotFoundException and returns a 404 NOT_FOUND response.
     * @param ex The BookNotFoundException instance.
     * @return A string representing the error message.
     */
    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(BookNotFoundException ex) {
        return ex.getMessage();
    }
}
