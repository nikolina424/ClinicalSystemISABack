package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOfRequest;
    private boolean pending = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}