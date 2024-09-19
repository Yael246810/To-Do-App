package com.example.demo.clr;

import com.example.demo.beans.Category;
import com.example.demo.beans.Customer;
import com.example.demo.beans.Task;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.ClientService;
import com.example.demo.services.CustomerService;
import com.example.demo.utils.testUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(2)
public class anotherTest implements CommandLineRunner {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public void run(String... args) throws Exception {
        //Login
        testUtils.test("Customer Service - Login");
        System.out.println(((ClientService)customerService).login("roni123@gmail.com","1111"));

        testUtils.test("Customer Service - Login");
        System.out.println(((ClientService)customerService).login("maor414@gmail.com","1111"));

        testUtils.test("Customer Service - Login");
        System.out.println(((ClientService)customerService).login("maor414@gmail.com","maori246810"));
        System.out.println("------------------------------------------------------------");

        testUtils.test("Customer Service - changing password");
        customerService.changePassword(1,"maor414@gmail.com","1111");

        //Id doesn't exist
        try {
            customerService.changePassword(0,"maor414@gmail.com","2222");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //email is not correct
        try {
            customerService.changePassword(1,"maor555@gmail.com","5555");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------------------------------------------");

        testUtils.test("Customer Service - add a new task");
        Customer customerForTest = Customer.builder()
                .firstName("Yael")
                .lastName("Cohen")
                .email("yael111@gmail.com")
                .password("4444")
                .build();
        customerRepository.save(customerForTest);
        Task taskForTest = Task.builder()
                .title("shopping")
                .startDate(LocalDate.now())
                .description("buy shampoo and soap")
                .category(Category.LIST)
                .endDate(LocalDate.now().plusDays(1))
                .customer(customerForTest)
                .build();
        customerService.addNewTask(1,taskForTest); //TODO: customer Id does not match

        testUtils.test("Customer Service - delete task");
        try {
            customerService.deleteTask(1,2);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Task ID doesn't exist

        try {
            customerService.deleteTask(1,0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // ID doesn't exist
        try{
            customerService.deleteTask(3,1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------------");

        testUtils.test("Customer Service - update task");
        Task taskForUpdate1 = Task.builder()
                .id(4)
                .title("123")
                .description("my task")
                .customer(customerForTest)
                .startDate(LocalDate.now().minusDays(3))
                .endDate(LocalDate.now().minusMonths(1))
                .category(Category.PROJECT)
                .build();
        try{
            System.out.println(customerService.updateTask(taskForUpdate1.getCustomer().getId(),taskForUpdate1));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // Id does not exist
        Task taskForUpdate = Task.builder().build();
        try{
            customerService.updateTask(taskForUpdate.getCustomer().getId(),taskForUpdate);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------------------");

        testUtils.test("Customer Service - get customer tasks");
        try {
            System.out.println(customerService.getCustomerTasks(1));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //ID does not exist
        try {
            customerService.getCustomerTasks(6);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("---------------------------------------");

        testUtils.test("Customer Service - get customer tasks by category");

        //customer without this category
        try{
            System.out.println(customerService.getCustomerTasksByCategory(1,Category.PROJECT));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            System.out.println(customerService.getCustomerTasksByCategory(1,Category.TAG));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        System.out.println("--------------------------------------------------");

        testUtils.test("Customer Service - get task until end date");
        try{
            System.out.println(customerService.getCustomerTasksUntilEndDate(1,LocalDate.of(2024,07,20)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //End date not valid

        try {
            customerService.getCustomerTasksUntilEndDate(1,LocalDate.now().minusDays(1));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("---------------------------------");
        testUtils.test("Customer Service - get task from start date");
        try{
            System.out.println(customerService.getCustomerTasksFromStartDate(1,LocalDate.of(2024,05,01)));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // ID doesn't exist
        try {
            customerService.getCustomerTasksFromStartDate(7,LocalDate.of(2024,05,01));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // Date later then today
        try {
            customerService.getCustomerTasksFromStartDate(1,LocalDate.of(2025,01,01));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("---------------------------------------");

        testUtils.test("Customer Service - get customer details");
        try {
            customerService.getCustomerDetails(1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // ID doesn't exist
        try {
            customerService.getCustomerDetails(5);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
