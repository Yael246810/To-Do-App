package com.example.demo.clr;

import com.example.demo.beans.Category;
import com.example.demo.beans.Customer;
import com.example.demo.beans.Task;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
public class test implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {

        Customer customer1 = Customer.builder()
                .email("maor414@gmail.com")
                .password("maori246810")
                .firstName("Maor")
                .lastName("Schwartz")
                .build();

        customerRepository.save(customer1);

        Task task1 = Task.builder()
                .title("Washing")
                .category(Category.TAG)
                .customer(customer1).build();

        Task task2 = Task.builder()
                .title("Picking Noa")
                .category(Category.TAG)
                .customer(customer1)
                .build();

        Task task3 = Task.builder()
                .title("taking pills")
                .customer(customer1)
                .category(Category.TAG)
                .description("taking the bed time pills")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(1))
                .build();

        List<Task>tasks = List.of(task1,task2,task3);
        taskRepository.saveAll(tasks);

        customer1.setTasks(List.of(task1,task2,task3));
    }
}
