package com.example.demo.repository;

import com.example.demo.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    MedicalRecord findOneByUserId(Long id);
}
