package com.example.demo.controller;

import com.example.demo.model.Clinic;
import com.example.demo.model.User;
import com.example.demo.service.ClinicService;
import com.example.demo.view.ClinicViewModify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

import static com.example.demo.model.UserRole.*;

@RestController
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path="/getClinics")
    public ResponseEntity<?> getClinic(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(PATIENT.equals(user.getRole()) || ADMINCC.equals(user.getRole()) || ADMINC.equals(user.getRole())){
            return new ResponseEntity<>(this.clinicService.getClinics(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path="/userClinic")
    public ResponseEntity<?> userClinic() {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINC.equals(loggedUser.getRole())) {
            return new ResponseEntity<>(this.clinicService.findOneByAdminId(loggedUser.getId()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/modifyClinic")
    public ResponseEntity<?> modifyClinic(@RequestBody ClinicViewModify clinicViewModify) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINC.equals(loggedUser.getRole())) {
            Clinic newClinic = this.clinicService.findOneById(clinicViewModify.getClinicId());
            newClinic.setAddress(clinicViewModify.getAddress());
            newClinic.setDescription(clinicViewModify.getDescription());
            newClinic.setName(clinicViewModify.getName());
            newClinic.setLongitude(clinicViewModify.getLongitude());
            newClinic.setLatitude(clinicViewModify.getLatitude());
            return new ResponseEntity<>(this.clinicService.save(newClinic), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
