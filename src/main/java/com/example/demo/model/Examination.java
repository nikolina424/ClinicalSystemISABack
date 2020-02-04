package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "dateTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar dateTime;

    @Column(name = "durationHours")
    private Double duration;
}
