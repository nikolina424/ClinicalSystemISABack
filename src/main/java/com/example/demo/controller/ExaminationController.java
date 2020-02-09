package com.example.demo.controller;

import com.example.demo.common.TimeProvider;
import com.example.demo.model.*;
import com.example.demo.service.ExaminationService;
import com.example.demo.service.RequestService;
import com.example.demo.service.RoomService;
import com.example.demo.view.ExaminationViewModify;
import com.example.demo.view.ExaminationViewSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.example.demo.model.RequestRole.EXAMINATION;
import static com.example.demo.model.UserRole.*;

@RestController
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private TimeProvider timeProvider;

    @Autowired
    private RequestService requestService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/getExaminationsOfClinic")
    public ResponseEntity<?> getExaminationsOfClinic() {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) || ADMINC.equals(loggedUser.getRole())) {
            return new ResponseEntity<>(this.examinationService.findExaminationsOfClinic(loggedUser.getId()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/modifyExamination")
    public ResponseEntity<?> modifyExamination(@RequestBody ExaminationViewModify ex) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINC.equals(loggedUser.getRole())) {
            Examination newEx = this.examinationService.findOneById(ex.getId());
            newEx.setDescription(ex.getDescription());
            newEx.setDoctor(ex.getDoctor());
            newEx.setDuration(ex.getDuration());
            newEx.setPrice(ex.getPrice());
            return new ResponseEntity<>(this.examinationService.save(newEx), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/getExaminations")
    public ResponseEntity<?> getExaminations() {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) || ADMINC.equals(loggedUser.getRole())) {
            return new ResponseEntity<>(this.examinationService.findAll(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/deleteExamination")
    public ResponseEntity<?> deleteExamination(@RequestBody Examination ex) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINC.equals(loggedUser.getRole())) {
            Room room = this.roomService.findRoomByExamination(ex.getId());
            room.setExamination(null);
            room.setReserved(false);
            this.roomService.save(room);
            this.examinationService.deleteExaminationRoom(ex.getId());
            this.examinationService.deleteExamination(ex.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    protected User getLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path="/scheduleExamination")
    public ResponseEntity<?> scheduleExamination(@RequestBody ExaminationViewSchedule exView) {

        User user = getLoggedUser();

        if (ADMINC.equals(user.getRole())) {
            Examination ex = this.examinationService.save(exView);
            Room room = exView.getRoom();
            room.setReserved(true);
            room.setExamination(ex);
            this.roomService.save(room);
            this.roomService.setExToRoom(room.getId(), ex.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        if (DOCTOR.equals(user.getRole())) {
            Examination ex = this.examinationService.saveDoctor(exView, user);
            Request newRequest = new Request();
            newRequest.setRole(EXAMINATION);
            newRequest.setExamination(ex);
            newRequest.setUser(user);
            newRequest.setDateOfRequest(new Date(timeProvider.now().getTime()));
            this.requestService.save(newRequest);
            Room room = exView.getRoom();
            room.setReserved(true);
            room.setExamination(ex);
            this.roomService.save(room);
            this.roomService.setExToRoom(room.getId(), ex.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
