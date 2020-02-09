package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.model.UserRole.ADMINCC;
import static com.example.demo.model.UserRole.PATIENT;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ClinicService clinicService;

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
    @PostMapping(path = "/approveHoliday")
    public ResponseEntity<?> approveHoliday(@RequestBody Request request) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) && loggedUser.isPredefined()) {
            request.getHoliday().setApproved(true);
            this.holidayService.save(request.getHoliday());
            this.requestService.delete(request.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/approveExamination")
    public ResponseEntity<?> approveExamination(@RequestBody Request request) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) && loggedUser.isPredefined()) {
            request.getExamination().setApproved(true);
            this.examinationService.save(request.getExamination());
            this.requestService.delete(request.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/approveOperation")
    public ResponseEntity<?> approveOperation(@RequestBody Request request) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) && loggedUser.isPredefined()) {
            request.getOperation().setApproved(true);
            this.operationService.save(request.getOperation());
            this.requestService.delete(request.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/declineOperation")
    public ResponseEntity<?> declineOperation(@RequestBody Request request) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) && loggedUser.isPredefined()) {
            Operation op = this.operationService.findOneById(request.getOperation().getId());
            Room room = this.roomService.findRoomByOperation(op.getId());
            room.setOperation(null);
            room.setReserved(false);
            this.roomService.save(room);
            this.operationService.deleteOperationRoom(op.getId());
            this.requestService.delete(request.getId());
            this.operationService.deleteOperation(op.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/declineExamination")
    public ResponseEntity<?> declineExamination(@RequestBody Request request) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) && loggedUser.isPredefined()) {
            Examination ex = this.examinationService.findOneById(request.getExamination().getId());
            Room room = this.roomService.findRoomByExamination(ex.getId());
            room.setExamination(null);
            room.setReserved(false);
            this.roomService.save(room);
            this.examinationService.deleteExaminationRoom(ex.getId());
            this.requestService.delete(request.getId());
            this.examinationService.deleteExamination(ex.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/declineHoliday")
    public ResponseEntity<?> declineHoliday(@RequestBody Request request) {

        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ADMINCC.equals(loggedUser.getRole()) && loggedUser.isPredefined()) {
            this.requestService.delete(request.getId());
            this.holidayService.delete(request.getHoliday().getId());
            return new ResponseEntity<>(HttpStatus.OK);
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

            if (!request.getUser().getRole().equals(PATIENT)) {
                Clinic clinic = this.clinicService.findOneByName(request.getClinicName());
                this.requestService.saveUserIntoClinicWork(request.getUser().getId(), clinic.getId());
            }

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
