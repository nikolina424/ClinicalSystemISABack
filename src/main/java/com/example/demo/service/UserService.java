package com.example.demo.service;

import com.example.demo.model.Authority;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.view.UserViewRegister;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    public User findOneById(Long id) {
        return this.userRepository.findOneById(id);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User saveAdminCC(UserViewRegister user) {
        User u = User.builder().email(user.getEmail()).password(passwordEncoder.encode(user.getPassword()))
                .firstName(user.getFirstName()).lastName(user.getLastName())
                .address(user.getAddress()).city(user.getCity()).country(user.getCountry())
                .phoneNumber(Long.valueOf(user.getPhoneNumber())).userId(Long.valueOf(user.getUserId())).role(UserRole.valueOf(user.getRole()))
                .enabled(true).firstTimeLogged(true).predefined(false).build();

        return this.userRepository.save(u);
    }

    public Long findClinicId(Long doctorId) {
        return this.userRepository.findClinicId(doctorId);
    }

    public void addWorkDoctor(Long clinicId, Long doctorId) {
        this.userRepository.addWorkDoctor(clinicId, doctorId);
    }

    public void deleteUserFromUserWork(Long id) {
        this.userRepository.deleteUser(id);
    }

    public User saveDoctor(UserViewRegister user) {
        User u = User.builder().email(user.getEmail()).password(passwordEncoder.encode(user.getPassword()))
                .firstName(user.getFirstName()).lastName(user.getLastName())
                .address(user.getAddress()).city(user.getCity()).country(user.getCountry())
                .phoneNumber(Long.valueOf(user.getPhoneNumber())).userId(Long.valueOf(user.getUserId())).role(UserRole.valueOf(user.getRole()))
                .enabled(true).firstTimeLogged(true).predefined(false).build();

        return this.userRepository.save(u);
    }

    public User save(UserViewRegister user) {
        User u = User.builder().email(user.getEmail()).password(passwordEncoder.encode(user.getPassword()))
                .firstName(user.getFirstName()).lastName(user.getLastName())
                .address(user.getAddress()).city(user.getCity()).country(user.getCountry())
                .phoneNumber(Long.valueOf(user.getPhoneNumber())).userId(Long.valueOf(user.getUserId())).role(UserRole.valueOf(user.getRole()))
                .enabled(false).firstTimeLogged(true).predefined(false).build();

        return this.userRepository.save(u);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User findOneByEmailAndPassword(String email, String password) throws NotFoundException {
        User user =  this.userRepository.findOneByEmail(email);
        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            throw new NotFoundException("Not existing user");
        }

        return user;
    }

    public User findOneByEmail(String email) {
        return this.userRepository.findOneByEmail(email);
    }

    public List<User> findAllByRole(UserRole role) {
        return this.userRepository.findAllByRole(role);
    }

    public List<User> findAllById(Long id) {
        return  this.userRepository.findAllById(id);
    }

    public void deleteUser(Long id) {
        this.userRepository.delete(id);
    }
}
