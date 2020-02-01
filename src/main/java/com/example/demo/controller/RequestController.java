package com.example.demo.controller;

import com.example.demo.model.Request;
import com.example.demo.model.User;
import com.example.demo.service.RequestService;
import com.example.demo.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.model.UserRole.ADMINCC;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/getRequests")
    public ResponseEntity<?> getRequests() {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) && loggedUser.isPredefined()) {
            return new ResponseEntity<>(this.requestService.findAll(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/approveRegistration")
    public ResponseEntity<?> approveRegistration(@RequestBody Request request) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) && loggedUser.isPredefined()) {
            String random = RandomString.make(16);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(request.getUser().getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom(loggedUser.getEmail());
            mailMessage.setText("To confirm your account registration, please click here: "
                    + "http://localhost:3000/confirm-account?token="+random + "-" + request.getUser().getId());
            this.requestService.sendEmail(mailMessage);
            this.requestService.delete(request.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/confirm-account")
    public ResponseEntity<?> confirmRegistration(@RequestParam(value = "token", required = true)String token) {

        Long userId = Long.valueOf(token.split("-")[1]);
        User regUser = this.userService.findOneById(userId);
        regUser.setEnabled(true);

        return new ResponseEntity<>(this.userService.save(regUser), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/declineRegistration")
    public ResponseEntity<?> declineRegistration(@RequestBody Request request) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) && loggedUser.isPredefined()) {
            this.requestService.delete(request.getId());
            this.userService.deleteUser(request.getUser().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
