package com.aditya.emsapi.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status; // PENDING, IN_PROGRESS, COMPLETED

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee assignedTo;
}

