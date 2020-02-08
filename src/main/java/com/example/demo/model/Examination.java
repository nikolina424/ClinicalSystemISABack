package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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

    @Column(name = "price")
    private Double price;

    @Column(name = "dateTime", nullable = false)
    private Date dateTime;

    @Column(name = "durationHours")
    private Double duration;

    @Column(name = "approved")
    private boolean approved = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private User doctor;
}
