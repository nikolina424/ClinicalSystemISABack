package com.example.demo.repository;

import com.example.demo.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    Examination findOneById(Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from examination ex where ex.id=:id")
    void deleteExamination(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from examination_room er where er.examination_id=:id")
    void deleteExaminationRoom(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select ex.id, ex.date_time, ex.description, ex.duration_hours, ex.price, ex.doctor_id from examination_room exr left join examination ex on exr.examination_id=ex.id where exr.room_id in (select r.id from room r left join clinic cl on r.clinic_id = cl.id where cl.id = (select cl.id from clinic cl left join users us on cl.admin_id=us.id where us.id=:id) and r.reserved=true)")
    List<Examination> findAllExaminationsOfClinic(@Param("id") Long id);
}
