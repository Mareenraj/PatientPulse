package com.mareen.patientservice.exception;

import com.mareen.patientservice.exception.custom.EmailAlreadyExistsException;
import com.mareen.patientservice.exception.custom.PatientIdNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        log.warn("Email address already exists!, {}", exception.getMessage());

        Map<String, String> errors = new HashMap<>();

        errors.put("message: ", exception.getMessage());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientIdNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePatientIdNotFoundException(PatientIdNotFoundException exception) {

        Map<String, String> errors = new HashMap<>();

        errors.put("message: ", "Patient not found!");

        return ResponseEntity.badRequest().body(errors);
    }
}
