package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date finishDate;
    private String description;
    private boolean approved = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
