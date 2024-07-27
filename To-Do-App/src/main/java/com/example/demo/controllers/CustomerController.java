package com.example.demo.controllers;

import com.example.demo.beans.Category;
import com.example.demo.beans.Customer;
import com.example.demo.beans.Task;
import com.example.demo.beans.User;
import com.example.demo.exceptions.ToDoException;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@CrossOrigin
public class CustomerController extends BaseController {
    @Autowired
    private CustomerService customerService;
    @PutMapping("{customerId}") //TODO: Is it ok to write it like this?
    Customer changePassword(@RequestHeader(value = "Authorization") String token, @PathVariable long customerId, @RequestBody User user) throws ToDoException {
        System.out.println("changing password");
        validateToken(token);
        return customerService.changePassword(customerId,user.getEmail(),user.getPassword());
    }
    @PostMapping("{customerId}/task")
    @ResponseStatus(HttpStatus.CREATED)
    void addNewTask(@RequestHeader(value = "Authorization") String token, @PathVariable long customerId, @RequestBody Task task) throws ToDoException {
        System.out.println("add the task: "+task);
        validateToken(token);
        customerService.addNewTask(customerId,task);
    }
    @DeleteMapping("{customerId}/tasks/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTask(@RequestHeader(value = "Authorization")String token, @PathVariable long customerId, @PathVariable int taskId) throws ToDoException {
        validateToken(token);
        customerService.deleteTask(customerId,taskId);
    }

    @PutMapping("{customerId}/task")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Task updateTask(@RequestHeader(value = "Authorization")String token, @PathVariable long customerId, @RequestBody Task task) throws ToDoException {
        validateToken(token);
        return customerService.updateTask(customerId,task);
    }

    @GetMapping("{customerId}/tasks")
    List<Task>getCustomerTasks(@RequestHeader(value = "Authorization")String token, @PathVariable long customerId) throws ToDoException {
        validateToken(token);
        return customerService.getCustomerTasks(customerId);
    }
    @GetMapping("{customerId}/tasks/category")
    List<Task>getCustomerTasksByCategory(@RequestHeader(value = "Authorization")String token, @RequestParam("val") Category category, @PathVariable long customerId) throws ToDoException {
        System.out.println("recived val: "+ category);
        validateToken(token);
        return customerService.getCustomerTasksByCategory(customerId, category);
    }
    @GetMapping("{customerId}/tasks/endDate")
    List<Task>getCustomerTasksUntilEndDate(@RequestHeader(value = "Authorization")String token, @RequestParam("val") LocalDate taskEndDate,@PathVariable long customerId) throws ToDoException {
        validateToken(token);
        return customerService.getCustomerTasksUntilEndDate(customerId, taskEndDate);
    }
    @GetMapping("{customerId}/tasks/startDate")
    List<Task>getCustomerTaskFromStartDate(@RequestHeader(value = "Authorization")String token,@RequestParam("val") LocalDate startDate, @PathVariable long customerId) throws ToDoException {
        validateToken(token);
        return customerService.getCustomerTasksFromStartDate(customerId, startDate);
    }
    @GetMapping("{customerId}")
    Customer getCustomerDetails(@RequestHeader(value = "Authorization")String token, @PathVariable long customerId) throws ToDoException {
        validateToken(token);
        return customerService.getCustomerDetails(customerId);
    }
}
