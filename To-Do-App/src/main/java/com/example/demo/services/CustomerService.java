package com.example.demo.services;

import com.example.demo.beans.Category;
import com.example.demo.beans.Customer;
import com.example.demo.beans.Task;
import com.example.demo.exceptions.ToDoException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CustomerService {
    Customer changePassword(long customerId, String email, String password) throws ToDoException;
    void addNewTask(long customerId,Task task) throws ToDoException; // When I create a new task, I automatically add it to the customer who created it
    void deleteTask(long customerId, int taskId) throws ToDoException;
    Task updateTask(long customerId,Task task) throws ToDoException;
    List<Task>getCustomerTasks(long customerId) throws ToDoException;
    List<Task>getCustomerTasksByCategory(long customerId, Category category) throws ToDoException;
    List<Task>getCustomerTasksUntilEndDate(long customerId, LocalDate taskEndDate) throws ToDoException;
    List<Task>getCustomerTasksFromStartDate(long customerId, LocalDate startDate) throws ToDoException;
    Customer getCustomerDetails(long customerId) throws ToDoException;
}
