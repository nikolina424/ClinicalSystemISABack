package com.example.demo.controller;

import com.example.demo.dto.UserTokenState;
import com.example.demo.exception.ResourceConflictException;
import com.example.demo.model.Authority;
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
import java.util.List;

import static com.example.demo.model.UserRole.ADMINC;
import static com.example.demo.model.UserRole.ADMINCC;

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

        User saveUser = this.userService.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/userId}").buildAndExpand(saveUser.getId()).toUri());
        return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
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
