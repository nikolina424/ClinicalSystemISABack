package com.example.demo.controller;

import com.example.demo.model.Clinic;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.service.ClinicService;
import com.example.demo.service.RoomService;
import com.example.demo.view.RoomViewAdd;
import com.example.demo.view.RoomViewModify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.model.UserRole.*;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ClinicService clinicService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/getClinicRoomsFromDoctor")
    public ResponseEntity<?> getClinicRoomsFromDoctor() {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (DOCTOR.equals(loggedUser.getRole())) {
            return new ResponseEntity<>(this.roomService.findAllByDoctorId(loggedUser.getId()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/getClinicRooms")
    public ResponseEntity<?> getClinicRooms() {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINC.equals(loggedUser.getRole())) {
            Clinic clinic = clinicService.findOneByAdminId(loggedUser.getId());
            return new ResponseEntity<>(this.roomService.findAllByClinicIdAndNotReserved(clinic.getId()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/getAllRooms")
    public ResponseEntity<?> getAllRooms() {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if ((ADMINCC.equals(loggedUser.getRole()) || ADMINC.equals(loggedUser.getRole())))
            return new ResponseEntity<>(this.roomService.findAll(), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/addNewRoom")
    public ResponseEntity<?> addNewRoom(@RequestBody RoomViewAdd roomViewAdd) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if ((ADMINCC.equals(loggedUser.getRole()) || ADMINC.equals(loggedUser.getRole()))) {
            Clinic clinic = this.clinicService.findOneByName(roomViewAdd.getClinicName());
            Room newRoom = new Room();
            newRoom.setClinic(clinic);
            newRoom.setNumber(roomViewAdd.getNumber());
            return new ResponseEntity<>(this.roomService.save(newRoom), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/modifyRoom")
    public ResponseEntity<?> modifyRoom(@RequestBody RoomViewModify roomViewModify) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if ((ADMINCC.equals(loggedUser.getRole()) || ADMINC.equals(loggedUser.getRole()))) {
            Clinic clinic = this.clinicService.findOneByName(roomViewModify.getClinicName());
            Room modifyRoom = this.roomService.findOneById(roomViewModify.getRoomId());
            modifyRoom.setNumber(roomViewModify.getNumber());
            modifyRoom.setClinic(clinic);
            return new ResponseEntity<>(this.roomService.save(modifyRoom), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/deleteRoom")
    public ResponseEntity<?> deleteRoom(@RequestBody Room room) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if ((ADMINCC.equals(loggedUser.getRole()) || ADMINC.equals(loggedUser.getRole())) && !room.isReserved()) {
            this.roomService.deleteRoom(room.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
