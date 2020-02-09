package com.example.demo.repository;

import com.example.demo.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from request r where r.id=:id")
    void deleteRequest(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into user_work (clinic_id, user_id) values (:clinicId, :userId);")
    void saveUserIntoClinicWork(@Param("userId") Long userId, @Param("clinicId") Long clinicId);
}
