package com.mareen.patientservice.controller;

import com.mareen.patientservice.dto.PatientRequestDto;
import com.mareen.patientservice.dto.PatientResponseDto;
import com.mareen.patientservice.dto.validators.CreatePatientValidationGroup;
import com.mareen.patientservice.service.PatientService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        return ResponseEntity.ok().body(patientService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.ok().body(patientService.create(patientRequestDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable("id") String id, @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.ok().body(patientService.updatePatient(UUID.fromString(id), patientRequestDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") String id) {
        patientService.deletePatient(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }
}
