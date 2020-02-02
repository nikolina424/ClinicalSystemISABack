package com.example.demo.repository;

import com.example.demo.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from holiday h where h.id=:id")
    void deleteHoliday(@Param("id") Long id);
}
