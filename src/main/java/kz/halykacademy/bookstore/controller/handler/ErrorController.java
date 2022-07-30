package kz.halykacademy.bookstore.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exceptions handler.
 */
@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    /**
     * Exception handler to build response entity with status 500 and error type UNDEFINED_ERROR.
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    ResponseEntity<Object> handleException(final Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
