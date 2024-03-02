package com.Pacotyse.LibriSync.advice;

import com.Pacotyse.LibriSync.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Advice class to handle exceptions related to Member entities.
 */
@ControllerAdvice
public class MemberExceptionHandler {

    /**
     * Handles MemberNotFoundException and returns a 404 NOT_FOUND response.
     * @param ex The MemberNotFoundException instance.
     * @return A string representing the error message.
     */
    @ResponseBody
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String memberNotFoundHandler(MemberNotFoundException ex) {
        return ex.getMessage();
    }

}
