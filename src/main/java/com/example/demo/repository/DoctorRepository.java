package com.example.demo.repository;

import com.example.demo.model.Doctor;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    User findOneByEmailAndPassword(String email, String password);
}
