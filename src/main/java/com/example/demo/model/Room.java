package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer number;
    private boolean reserved;

    @ManyToOne(fetch = FetchType.EAGER)
    private Operation operation;

    @ManyToOne(fetch = FetchType.EAGER)
    private Examination examination;

    @ManyToOne(fetch = FetchType.EAGER)
    private Clinic clinic;

    @ManyToMany
    @JoinTable(
            name = "examination_room",
            joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "examination_id", referencedColumnName = "id")
    )
    private List<Examination> examinationList;

    @ManyToMany
    @JoinTable(
            name = "operation_room",
            joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id")
    )
    private List<Operation> operationList;
}
