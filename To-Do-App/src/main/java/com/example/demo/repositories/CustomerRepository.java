package com.example.demo.repositories;

import com.example.demo.beans.Category;
import com.example.demo.beans.Customer;
import com.example.demo.beans.Task;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM `demo`.tasks WHERE customer_id = ?", nativeQuery = true)
    List<Task> findCustomerTasksById(@Param("customerId") long customerId);
    // TODO: doesn't work

    @Query(value = "SELECT * FROM `demo`.tasks WHERE customer_id = ? AND category = ?", nativeQuery = true)
    List<Task> findByCategoryAndCustomerId(long customerId, Category category);
    // TODO: doesn't work

    @Query(value = "SELECT * FROM `demo`.tasks WHERE customer_id = ? AND start_date = ?",nativeQuery = true)
    List<Task>findCustomerTasksFromStartDate(long customerId, LocalDate startDate);
    @Query(value = "SELECT * FROM `demo`.tasks WHERE customer_id = ? AND end_date = ?",nativeQuery = true)
    List<Task>findCustomerTasksBeforeEndDate(long customerId, LocalDate endDate);
    @Query(value = "SELECT id from demo.users WHERE email = ?", nativeQuery = true)
    long getIdByEmail(String email);

    Customer findById(long customerId);
}
