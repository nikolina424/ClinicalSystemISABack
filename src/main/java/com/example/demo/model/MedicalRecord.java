package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "medicalRecord")
    private User user;

    @OneToMany(mappedBy = "medRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Visit> visitList = new ArrayList<Visit>();

    @ManyToMany
    @JoinTable(
            name = "patientSick",
            joinColumns = @JoinColumn(name = "med_record_id"),
            inverseJoinColumns = @JoinColumn(name = "sick_id")
    )
    private List<Sick> sickList = new ArrayList<Sick>();
}
