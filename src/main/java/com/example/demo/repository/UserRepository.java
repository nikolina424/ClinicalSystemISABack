package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneById(Long id);
    User findOneByEmailAndPassword(String email, String password);
    User findOneByEmail(String email);
    List<User> findAllByRole(UserRole role);

    @Query(nativeQuery = true, value = "select id from clinic cl where cl.admin_id=:doctorId")
    Long findClinicId(@Param("doctorId") Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from user_work uw where uw.user_id=:id")
    void deleteUser(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into user_work (clinic_id, user_id) values (:clinicId, :doctorId)")
    void addWorkDoctor(@Param("clinicId") Long clinicId, @Param("doctorId") Long doctorId);

    @Query(nativeQuery = true, value = "select * from (select user_id from user_work uw left join clinic cl on cl.id = uw.clinic_id where cl.id = (select id from clinic cl where cl.admin_id=:id)) use left join users us on use.user_id = us.id where us.role = 'DOCTOR'")
    List<User> findAllById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from users u where u.id=:id")
    void delete(@Param("id") Long id);
}
