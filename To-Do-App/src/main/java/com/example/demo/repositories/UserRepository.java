package com.example.demo.repositories;

import com.example.demo.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmailAndPassword(String email, String password);
    @Query(value = "SELECT id FROM `demo`.users WHERE email =?",nativeQuery = true)
    long getIdByEmail(String email);
}
