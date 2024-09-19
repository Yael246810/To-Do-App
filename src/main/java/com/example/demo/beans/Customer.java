package com.example.demo.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer extends User{
    @Column(length = 10)
    private String firstName;
    @Column(length = 10)
    private String lastName;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval = true ) //fetch = FetchType.LAZY
    private List<Task> tasks;

    @Builder
    public Customer (String firstName, String lastName, List<Task>tasks, long id, String email, String password){
        super(id,email,password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.tasks = tasks;
    }
}
