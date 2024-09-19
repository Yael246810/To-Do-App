package com.example.demo.repositories;

import com.example.demo.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);
    @Query(value = "SELECT id from demo.users WHERE email = ?", nativeQuery = true)
    long getIdByEmail(String email);
    Customer findById(long customerId);
}
