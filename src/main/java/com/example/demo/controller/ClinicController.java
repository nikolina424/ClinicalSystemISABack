package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

import static com.example.demo.model.UserRole.PATIENT;

@RestController
public class ClinicController {
    @Autowired
    private ClinicService clinicService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path="/getClinics")
    public ResponseEntity<?> getClinic(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(PATIENT.equals(user.getRole())){
            return new ResponseEntity<>(this.clinicService.getClinics(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
