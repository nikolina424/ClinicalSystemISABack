package com.example.demo.controller;

import com.example.demo.dto.UserTokenState;
import com.example.demo.exception.ResourceConflictException;
import com.example.demo.model.User;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.*;
import com.example.demo.view.UserViewLogin;
import com.example.demo.view.UserViewRegister;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/login")
    public ResponseEntity<UserTokenState> login(@RequestBody UserViewLogin user) throws AuthenticationException, IOException {

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        //ubaci username(email) + password u kontext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Kreiraj token
        User userToken = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(userToken.getEmail());
        int expiresIn = tokenUtils.getExpiredIn();
        //vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserViewRegister user, UriComponentsBuilder ucBuilder) {

        if (!user.getRepeatPassword().equals(user.getPassword()))
            return null;

        User userFind = this.userService.findOneByEmail(user.getEmail());
        if (userFind != null) {
            throw new ResourceConflictException(user.getId(), "User with that email already exists");
        }

        User saveUser = this.userService.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/userId}").buildAndExpand(saveUser.getId()).toUri());
        return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
    }
}
