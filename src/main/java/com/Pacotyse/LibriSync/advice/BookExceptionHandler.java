package com.Pacotyse.LibriSync.advice;

import com.Pacotyse.LibriSync.exception.BookFoundException;
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
     * Handles BookFoundException and returns a 400 BAD_REQUEST response.
     * @param ex The BookFoundException instance.
     * @return A string representing the error message.
     */
    @ResponseBody
    @ExceptionHandler(BookFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String bookFoundHandler(BookFoundException ex) {
        return ex.getMessage();
    }
}
