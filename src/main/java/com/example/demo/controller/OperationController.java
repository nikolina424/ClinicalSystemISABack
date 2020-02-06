package com.example.demo.controller;

import com.example.demo.model.Operation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.service.OperationService;
import com.example.demo.service.RoomService;
import com.example.demo.view.OperationViewModify;
import com.example.demo.view.OperationViewSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.model.UserRole.*;

@RestController
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private RoomService roomService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/getOperationsOfClinic")
    public ResponseEntity<?> getOperationsOfClinic() {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) || ADMINC.equals(loggedUser.getRole())) {
            return new ResponseEntity<>(this.operationService.findOperationsOfClinic(loggedUser.getId()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/modifyOperation")
    public ResponseEntity<?> modifyOperation(@RequestBody OperationViewModify op) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINC.equals(loggedUser.getRole())) {
            Operation newOp = this.operationService.findOneById(op.getId());
            newOp.setDescription(op.getDescription());
            newOp.setDoctor(op.getDoctor());
            newOp.setDuration(op.getDuration());
            newOp.setPrice(op.getPrice());
            return new ResponseEntity<>(this.operationService.save(newOp), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/getOperations")
    public ResponseEntity<?> getOperations() {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) || ADMINC.equals(loggedUser.getRole())) {
            return new ResponseEntity<>(this.operationService.findAll(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/deleteOperation")
    public ResponseEntity<?> deleteOperation(@RequestBody Operation op) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINC.equals(loggedUser.getRole())) {
            Room room = this.roomService.findRoomByOperation(op.getId());
            room.setOperation(null);
            room.setReserved(false);
            this.roomService.save(room);
            this.operationService.deleteOperationRoom(op.getId());
            this.operationService.deleteOperation(op.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path="/scheduleSurgery")
    public ResponseEntity<?> scheduleOperation(@RequestBody OperationViewSchedule opView) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINC.equals(user.getRole())) {
            Operation op = this.operationService.save(opView);
            Room room = opView.getRoom();
            room.setReserved(true);
            room.setOperation(op);
            this.roomService.save(room);
            this.roomService.setOpToRoom(room.getId(), op.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        if (DOCTOR.equals(user.getRole())) {
            Operation op = this.operationService.saveDoctor(opView, user);
            Room room = opView.getRoom();
            room.setReserved(true);
            room.setOperation(op);
            this.roomService.save(room);
            this.roomService.setOpToRoom(room.getId(), op.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
