package com.example.demo.controller;

import com.example.demo.dto.UserDTOLogin;
import com.example.demo.dto.UserDTORegister;
import com.example.demo.model.Doctor;
import com.example.demo.model.MedicalSister;
import com.example.demo.model.Patient;
import com.example.demo.model.User;
import com.example.demo.service.*;
import com.example.demo.view.UserViewLogin;
import com.example.demo.view.UserViewRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private MedicalSisterService medicalSisterService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AdminClinicService adminClinicService;

    @Autowired
    private AdminClinicCenterService adminClinicCenterService;

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTOLogin> login(@RequestBody UserViewLogin user) {
        User existingD = this.doctorService.findOneByEmailAndPassword(user.getEmail(), user.getPassword());
        System.out.println(existingD);

        if (existingD != null) {
            System.out.println(existingD);
            return ResponseEntity.ok(new UserDTOLogin(existingD));
        }

        User existingMS = this.medicalSisterService.findOneByEmailAndPassword(user.getEmail(), user.getPassword());

        if (existingMS != null) {
            return ResponseEntity.ok(new UserDTOLogin(existingMS));
        }

        User existingP = this.patientService.findOneByEmailAndPassword(user.getEmail(), user.getPassword());

        if (existingP != null) {
            return ResponseEntity.ok(new UserDTOLogin(existingP));
        }

        User existingA = this.adminClinicService.findOneByEmailAndPassword(user.getEmail(), user.getPassword());

        if (existingA != null) {
            return ResponseEntity.ok(new UserDTOLogin(existingA));
        }

        User existingAC = this.adminClinicCenterService.findOneByEmailAndPassword(user.getEmail(), user.getPassword());

        if (existingAC != null) {
            return ResponseEntity.ok(new UserDTOLogin(existingAC));
        }

        return null;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTORegister> register(@RequestBody UserViewRegister user) {

        if (user.getRepeatPassword() != user.getPassword())
            return null;

        User newUser = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getAddress(), user.getCity(), user.getCountry(), user.getPhoneNumber(), user.getUserId(), user.getRole());

        if (user.getRole().equals("Doctor")) {
            User doctorUser = this.doctorService.save(new Doctor(newUser.getId(), newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword(), newUser.getAddress(), newUser.getCity(), newUser.getCountry(), newUser.getPhoneNumber(), newUser.getUserId(), newUser.getRole()));
            return ResponseEntity.ok(new UserDTORegister(doctorUser));
        }

        if (user.getRole().equals("Patient")) {
            User patientUser = this.patientService.save(new Patient(newUser.getId(), newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword(), newUser.getAddress(), newUser.getCity(), newUser.getCountry(), newUser.getPhoneNumber(), newUser.getUserId(), newUser.getRole()));
            return ResponseEntity.ok(new UserDTORegister(patientUser));
        }

        if (user.getRole().equals("Patient")) {
            User nurseUser = this.medicalSisterService.save(new MedicalSister(newUser.getId(), newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword(), newUser.getAddress(), newUser.getCity(), newUser.getCountry(), newUser.getPhoneNumber(), newUser.getUserId(), newUser.getRole()));
            return ResponseEntity.ok(new UserDTORegister(nurseUser));
        }

        return null;
    }
}
