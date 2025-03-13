package com.mareen.patientservice.controller;

import com.mareen.patientservice.dto.PatientRequestDto;
import com.mareen.patientservice.dto.PatientResponseDto;
import com.mareen.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.ok().body(patientService.create(patientRequestDto));
    }
}
