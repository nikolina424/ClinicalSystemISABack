package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.model.User;
import com.example.demo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

//    public Doctor findOne(Long id) {
//        return doctorRepository.findById(id).orElseGet(null);
//    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Page<Doctor> findAll(Pageable page) {
        return doctorRepository.findAll(page);
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

//    public void remove(Long id) {
//        doctorRepository.deleteById(id);
//    }

    public User findOneByEmailAndPassword(String email, String password) {
        return this.doctorRepository.findOneByEmailAndPassword(email, password);
    }
}
