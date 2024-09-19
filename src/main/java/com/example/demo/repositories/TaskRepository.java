package com.example.demo.repositories;

import com.example.demo.beans.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO demo.tasks (category, id, end_date, start_date, title, description) VALUES (?, ?, ?, ?, ? ,?)", nativeQuery = true)
    void addNewTask(long customerId,Task task);
}
