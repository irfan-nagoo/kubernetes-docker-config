package com.sample.raptor.exception;

import com.sample.raptor.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author irfan.nagoo
 */

@ControllerAdvice
@Slf4j
public class RaptorControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException me) {
        log.error("Validation error occurred: ", me);
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.name(), "Validation Error has Occurred");
        me.getBindingResult().getFieldErrors()
                .forEach(e -> response.getErrors().add(new ErrorResponse.ValidationError(e.getField(), e.getDefaultMessage())));
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Processing error occurred: ", e);
        return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                "Processing error has occurred"));
    }

}
