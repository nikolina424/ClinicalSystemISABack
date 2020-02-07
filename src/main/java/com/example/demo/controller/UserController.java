package com.example.demo.controller;

import com.example.demo.dto.UserEditDTO;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.service.StorageService;
import com.example.demo.service.UserService;
import com.example.demo.view.UserViewRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import static com.example.demo.model.UserRole.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StorageService storageService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/getDoctorsClinic")
    public ResponseEntity<?> getDoctorsClinic() {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINC.equals(loggedUser.getRole())) {
            return new ResponseEntity<>(this.userService.findAllById(loggedUser.getId()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

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
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/getClinicCenterAdmins")
    public ResponseEntity<?> getClinicCenterAdmins() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(user.getRole())) {
            List<User> clinicCenterAdmins = this.userService.findAllByRole(UserRole.valueOf("ADMINCC"));
            return new ResponseEntity<>(clinicCenterAdmins, HttpStatus.OK);
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
            loggedUser.setEmail(userEdit.getEmail());
            loggedUser.setPhoneNumber(Long.valueOf(userEdit.getPhoneNumber()));
            loggedUser.setUserId(Long.valueOf(userEdit.getUserId()));
            loggedUser.setAddress(userEdit.getAddress());
            loggedUser.setCity(userEdit.getCity());
            loggedUser.setCountry(userEdit.getCountry());
            return new ResponseEntity<>(this.userService.save(loggedUser), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/addNewClinicCenterAdmin")
    public ResponseEntity<?> addNewClinicCenterAdmin(@RequestBody UserViewRegister user) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) && loggedUser.isPredefined()) {
            return new ResponseEntity<>(this.userService.saveAdminCC(user), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/addNewDoctor")
    public ResponseEntity<?> addNewDoctor(@RequestBody UserViewRegister user) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINC.equals(loggedUser.getRole())) {
            User newDoctor = this.userService.saveDoctor(user);
            Long clinicId = this.userService.findClinicId(loggedUser.getId());
            this.userService.addWorkDoctor(clinicId, newDoctor.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINC.equals(loggedUser.getRole())) {
            this.userService.deleteUserFromUserWork(user.getId());
            this.userService.deleteUser(user.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        if (ADMINCC.equals(loggedUser.getRole())) {
            this.userService.deleteUser(user.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/addImageToUser")
    public ResponseEntity<?> addImageToUser(@RequestParam("file") MultipartFile file) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser != null) {
            try {
                String imageName = this.storageService.store(file);
                loggedUser.setImage(imageName);
                return new ResponseEntity<>(this.userService.save(loggedUser), HttpStatus.OK);
            } catch (Exception e) {
                throw new IllegalArgumentException("Fail to upload image!");
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
