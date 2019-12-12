package com.example.demo.controller;

import com.example.demo.dto.UserEditDTO;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.model.UserRole.DOCTOR;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/getPatients")
    public ResponseEntity<?> getPatients() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (DOCTOR.equals(user.getRole())) {
            List<User> patients = this.userService.findAllByRole(UserRole.valueOf("PATIENT"));
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/getUser")
    public ResponseEntity<?> getUser() {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedUser != null)
            return new ResponseEntity<>(this.userService.findOneById(loggedUser.getId()), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/editUserProfile")
    public ResponseEntity<?> editUserProfile(@RequestBody UserEditDTO userEdit) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedUser != null) {
            loggedUser.setFirstName(userEdit.getFirstName());
            loggedUser.setLastName(userEdit.getLastName());
            return new ResponseEntity<>(this.userService.save(loggedUser), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
