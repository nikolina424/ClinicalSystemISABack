package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.OperationService;
import com.example.demo.view.OperationViewSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.model.UserRole.DOCTOR;

@RestController
public class OperationController {

    @Autowired
    private OperationService operationService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path="/scheduleSurgery")
    public ResponseEntity<?> scheduleOperation(@RequestBody OperationViewSchedule opView) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (DOCTOR.equals(user.getRole()))
            return new ResponseEntity<>(this.operationService.save(opView), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
