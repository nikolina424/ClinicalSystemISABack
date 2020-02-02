package com.example.demo.controller;

import com.example.demo.common.TimeProvider;
import com.example.demo.model.Holiday;
import com.example.demo.model.Request;
import com.example.demo.model.User;
import com.example.demo.service.HolidayService;
import com.example.demo.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.example.demo.model.RequestRole.HOLIDAY;
import static com.example.demo.model.UserRole.DOCTOR;

@RestController
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private TimeProvider timeProvider;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/requestHoliday")
    public ResponseEntity<?> requestHoliday(@RequestBody Holiday holiday) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedUser != null && DOCTOR.equals(loggedUser.getRole())) {
            this.holidayService.save(holiday);
            Request newRequest = new Request();
            newRequest.setRole(HOLIDAY);
            newRequest.setHoliday(holiday);
            newRequest.setUser(holiday.getUser());
            newRequest.setDateOfRequest(new Date(timeProvider.now().getTime()));
            return new ResponseEntity<>(this.requestService.save(newRequest), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
