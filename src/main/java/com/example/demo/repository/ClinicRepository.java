package com.example.demo.repository;

import com.example.demo.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Clinic findOneByName(String name);
    Clinic findOneByAdminId(Long id);
    Clinic findOneById(Long id);
}
