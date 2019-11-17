package com.example.demo.repository;

import com.example.demo.model.Patient;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    User findOneByEmailAndPassword(String email, String password);
}
