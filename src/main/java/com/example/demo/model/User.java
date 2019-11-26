package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    private String address;
    private String city;
    private String country;
    private Integer phoneNumber;
    private Integer userId;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private boolean enabled;
    private Timestamp lastPasswordResetDate;
    private boolean firstTimeLogged = true;

    //veze sa bazom
    // samo doktor, sestra i admin klinike moze ovde
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Clinic clinic;

    //samo admin klinickog centra moze ovde
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ClinicCenter clinicCenter;

    //doktori
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Room room;

    //doktori
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Operation operation;

    //pacijent sa zdravstvenim kartonom, nova tabela
    @OneToOne
    @JoinTable(
            name = "patient_med_record",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "med_record_id")
    )
    private MedicalRecord medicalRecord;

    //operacije i pacijenti, nova tabela
    @OneToOne
    @JoinTable(
            name = "patient_operation",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id")
    )
    private Operation operationPatient;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
