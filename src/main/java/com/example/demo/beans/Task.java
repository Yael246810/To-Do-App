package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Category category;
    @Column(length = 20, nullable = false)
    private String title;
    @Column(length = 45)
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)// many tasks have one customer
    @ToString.Exclude
    @JsonIgnore
    private Customer customer;

    @Builder
    public Task (int id, Category category, String title, String description,Customer customer, LocalDate startDate, LocalDate endDate){
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
