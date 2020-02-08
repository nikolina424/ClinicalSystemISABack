package com.example.demo.repository;

import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.PermitAll;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findOneById(Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from room r where r.id=:id")
    void deleteRoom(@Param("id") Long id);

    Room findOneByExaminationId(Long id);
    Room findOneByOperationId(Long id);

    List<Room> findAllByClinicIdAndReserved(Long id, boolean reserved);
    List<Room> findAllByClinicId(Long id);

    @Query(nativeQuery = true, value = "select * from room r left join user_work uw on r.clinic_id = uw.clinic_id where r.reserved = false and uw.user_id=:id")
    List<Room> findAllByDoctorId(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from room r left join user_work uw on r.clinic_id = uw.clinic_id where uw.user_id=:id")
    List<Room> findAllByWorkUser(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into examination_room (room_id, examination_id) values (:roomId, :examinationId)")
    void setExToRoom(@Param("roomId") Long roomId, @Param("examinationId") Long examinationId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into operation_room (room_id, operation_id) values (:roomId, :operationId)")
    void setOpToRoom(@Param("roomId") Long roomId, @Param("operationId") Long operationId);
}
