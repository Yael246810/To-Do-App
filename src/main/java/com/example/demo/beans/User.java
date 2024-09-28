package com.example.demo.beans;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter

public class User { // TODO: I need to bring back the abstract
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Column(length = 30, unique = true, nullable = false)
    protected String email;
    @Column(length = 30, nullable = false)
    protected String password;

    protected User(long id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
