//package com.example.demo.jobs;
//
//import com.example.demo.exceptions.ToDoException;
//import com.example.demo.repositories.CustomerRepository;
//import com.example.demo.repositories.TaskRepository;
//import com.example.demo.services.CustomerServiceImpl;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.Date;
//
//@Component
//public class CompletedTaskRemoval {
//    //TODO: to remove all the completed tasks that remained? and to remind the tasks where endDate = today
//    @Autowired
//    TaskRepository taskRepository;
//
//    @Autowired
//    CustomerServiceImpl customerService;
//    @Autowired
//    CustomerRepository customerRepository;
//    private long customerId;
//
//    @Transactional
//    @Scheduled(fixedRate = 1000*60)
//    public void dailyRemainderTasks() throws ToDoException {
//        customerService.getCustomerTasksUntilEndDate(LocalDate.now(),customerId); // TODO: I need to check that.. I cannot put arguments int the method
//    }
//}
