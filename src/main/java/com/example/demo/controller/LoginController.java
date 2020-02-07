package com.example.demo.controller;

import com.example.demo.common.TimeProvider;
import com.example.demo.dto.UserTokenState;
import com.example.demo.exception.ResourceConflictException;
import com.example.demo.model.Authority;
import com.example.demo.model.Request;
import com.example.demo.model.User;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.*;
import com.example.demo.view.UserViewChangePassword;
import com.example.demo.view.UserViewLogin;
import com.example.demo.view.UserViewRegister;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import static com.example.demo.model.RequestRole.REGISTRATION;
import static com.example.demo.model.UserRole.*;

@RestController
public class LoginController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private TimeProvider timeProvider;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/login")
    public ResponseEntity<UserTokenState> login(@RequestBody UserViewLogin user) throws AuthenticationException, NotFoundException {

        User logged = this.userService.findOneByEmailAndPassword(user.getEmail(), user.getPassword());
        if (logged == null)
            throw new NotFoundException("Not existing user");

        List<Authority> auth = this.authorityService.findAllByRoleName(logged.getRole().getRole());

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), auth));

        //ubaci username(email) + password u kontext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Kreiraj token
        User userToken = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(userToken.getEmail(), userToken.getRole().getRole());
        int expiresIn = tokenUtils.getExpiredIn();
        //vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, (long) expiresIn, userToken.isFirstTimeLogged()));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserViewRegister user, UriComponentsBuilder ucBuilder) {

        if (!user.getRepeatPassword().equals(user.getPassword()))
            return null;

        User userFind = this.userService.findOneByEmail(user.getEmail());
        if (userFind != null) {
            throw new ResourceConflictException(user.getId(), "User with that email already exists");
        }

        User newUser = this.userService.save(user);
        Request newRequest = new Request();
        newRequest.setUser(newUser);
        newRequest.setDateOfRequest(new Date(timeProvider.now().getTime()));
        newRequest.setRole(REGISTRATION);
        Request saveRequest = this.requestService.save(newRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/userId}").buildAndExpand(newUser.getId()).toUri());
        return new ResponseEntity<Request>(saveRequest, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/changeAdminPassword")
    public ResponseEntity<?> changeAdminPassword(@RequestBody UserViewChangePassword user) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!PATIENT.equals(loggedUser.getRole())) {
            if (user.getNewPass().equals(user.getNewRepeatPass())) {
                if (passwordEncoder.matches(user.getOldPass(), loggedUser.getPassword())) {
                    loggedUser.setPassword(passwordEncoder.encode(user.getNewPass()));
                    return new ResponseEntity<>(this.userService.save(loggedUser), HttpStatus.OK);
                }
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody UserViewChangePassword user) {

        if (!user.getNewPass().equals(user.getNewRepeatPass()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean check = passwordEncoder.matches(user.getOldPass(), principal.getPassword());

        if (check) {
            User logged = this.userService.findOneByEmail(principal.getEmail());
            logged.setPassword(passwordEncoder.encode(user.getNewPass()));
            logged.setFirstTimeLogged(false);

            return new ResponseEntity<>(this.userService.save(logged), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
