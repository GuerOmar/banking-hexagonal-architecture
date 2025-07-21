package com.banking.adapters.in.web;

import com.banking.adapters.in.web.dto.ErrorMessageResponse;
import com.banking.application.exception.ApplicationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<?> handleApplicationException(ApplicationException ex) {
        return ResponseEntity.status(404).body(new ErrorMessageResponse(ex.getMessage()));
    }
}

