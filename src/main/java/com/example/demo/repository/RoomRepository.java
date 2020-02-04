package com.example.demo.repository;

import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findOneById(Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from room r where r.id=:id")
    void deleteRoom(@Param("id") Long id);
}
