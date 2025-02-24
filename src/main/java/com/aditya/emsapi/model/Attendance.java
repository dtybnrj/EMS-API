package com.aditya.emsapi.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String status; // CHECKED_IN, CHECKED_OUT

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}

