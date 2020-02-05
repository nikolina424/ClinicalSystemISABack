package com.example.demo.controller;

import com.example.demo.model.Examination;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.service.ExaminationService;
import com.example.demo.service.RoomService;
import com.example.demo.view.ExaminationViewSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.model.UserRole.ADMINC;
import static com.example.demo.model.UserRole.DOCTOR;

@RestController
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private RoomService roomService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path="/scheduleExamination")
    public ResponseEntity<?> scheduleExamination(@RequestBody ExaminationViewSchedule exView) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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
