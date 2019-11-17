package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.model.User;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

//    public Patient findOne(Long id) {
//        return patientRepository.findById(id).orElseGet(null);
//    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Page<Patient> findAll(Pageable page) {
        return patientRepository.findAll(page);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

//    public void remove(Long id) {
//        patientRepository.deleteById(id);
//    }

    public User findOneByEmailAndPassword(String email, String password) {
        return this.patientRepository.findOneByEmailAndPassword(email, password);
    }
}
