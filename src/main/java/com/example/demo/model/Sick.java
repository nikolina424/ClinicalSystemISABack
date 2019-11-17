package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Sick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "dateStart", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar dateStart;

    @Column(name = "dateEnd")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar dateEnd;

    @ManyToMany(mappedBy = "sickList")
    private List<MedicalRecord> medicalRecordList = new ArrayList<MedicalRecord>();

    public Sick() {
    }

    public Sick(Long id, String name, String description, Calendar dateStart, Calendar dateEnd) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDateStart() {
        return dateStart;
    }

    public void setDateStart(Calendar dateStart) {
        this.dateStart = dateStart;
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Calendar dateEnd) {
        this.dateEnd = dateEnd;
    }
}
