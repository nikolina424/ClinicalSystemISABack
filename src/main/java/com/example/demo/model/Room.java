package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

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
}
