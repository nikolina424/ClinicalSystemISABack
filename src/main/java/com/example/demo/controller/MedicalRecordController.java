package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.model.UserRole.PATIENT;

@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/getMedicalRecord")
    public ResponseEntity<?> getMedicalRecord(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(PATIENT.equals(user.getRole())){
           return new ResponseEntity<>(this.medicalRecordService.getMedicalRecord(user.getId()), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}
