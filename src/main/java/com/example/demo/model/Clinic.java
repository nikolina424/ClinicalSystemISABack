package com.example.demo.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    private String description;
    private String address;
    private float longitude;
    private float latitude;

    @ManyToOne(fetch = FetchType.EAGER)
    private ClinicCenter clinicCenter;

    @ManyToOne(fetch = FetchType.EAGER)
    private User admin;

    @ManyToMany
    @JoinTable(
            name = "user_work",
            joinColumns = @JoinColumn(name = "clinic_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> userList;
}
