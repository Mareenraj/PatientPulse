package com.mareen.patientservice.exception.custom;

public class PatientIdNotFoundException extends RuntimeException {
    public PatientIdNotFoundException(String message) {
        super(message);
    }
}
