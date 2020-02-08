package com.example.demo.repository;

import com.example.demo.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    Operation findOneById(Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from operation op where op.id=:id")
    void deleteOperation(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from operation_room opr where opr.operation_id=:id")
    void deleteOperationRoom(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select op.id, op.approved, op.date_time, op.description, op.duration_hours, op.price, op.doctor_id from operation_room opr left join operation op on opr.operation_id=op.id where opr.room_id in (select r.id from room r left join clinic cl on r.clinic_id = cl.id where cl.id = (select cl.id from clinic cl left join users us on cl.admin_id=us.id where us.id=:id) and r.reserved=true)")
    List<Operation> findAllOperationsOfClinic(@Param("id") Long id);
}
