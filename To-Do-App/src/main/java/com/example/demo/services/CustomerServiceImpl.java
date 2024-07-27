package com.example.demo.services;

import com.example.demo.beans.Category;
import com.example.demo.beans.Customer;
import com.example.demo.beans.Task;
import com.example.demo.exceptions.ErrorMessage;
import com.example.demo.exceptions.ToDoException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {
    Set<String>loggedInCustomers = new HashSet<>();
    @Override
    public boolean login(String email, String password) {
        boolean login = false;
        if (customerRepository.existsByEmail(email)){
            long customerId = customerRepository.getIdByEmail(email);
            Customer customer = customerRepository.findById(customerId);

            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)){
                loggedInCustomers.add(customer.getEmail());
                login = true;
            }
        }
        return login;
    }

    @Override
    public Customer changePassword(long customerId, String email, String password) throws ToDoException {

        if (!customerRepository.existsById((int) customerId)){
            throw new ToDoException(ErrorMessage.CUSTOMER_ID_NOT_FOUND);
        }

        Customer existingCustomer = customerRepository.findById(customerId);

        if (!loggedInCustomers.contains(existingCustomer.getEmail())|| !email.equals(existingCustomer.getEmail())){
            throw new ToDoException(ErrorMessage.CUSTOMER_DIDNT_LOGIN);
    }

        if (existingCustomer.getPassword().equals(password)){
            throw new ToDoException(ErrorMessage.PASSWORD_ALREADY_IN_USE);
        }

        existingCustomer.setPassword(password);
        return customerRepository.saveAndFlush(existingCustomer);
    }

    @Override
    public void addNewTask(long customerId,Task task) throws ToDoException {

        if (!customerRepository.existsById((int) customerId)){
            throw new ToDoException(ErrorMessage.CUSTOMER_ID_NOT_FOUND);
        }

        Customer existingCustomer = customerRepository.findById(customerId);

        if (!loggedInCustomers.contains(existingCustomer.getEmail())){
            throw new ToDoException(ErrorMessage.CUSTOMER_DIDNT_LOGIN);
        }

        task.setCustomer(existingCustomer);
        taskRepository.save(task); // TODO: this adds the new task only to the table of tasks
    }

    @Override
    public void deleteTask(long customerId, int taskId) throws ToDoException {

        if (!taskRepository.existsById(taskId)){
            throw new ToDoException(ErrorMessage.TASK_ID_NOT_FOUND);
        }

        if (!customerRepository.existsById((int) customerId)){
            throw new ToDoException(ErrorMessage.CUSTOMER_ID_NOT_FOUND);
        }
        Customer customer = customerRepository.findById(customerId);
        Task task = taskRepository.findById(taskId).orElseThrow();
        List<Task>tasks = customer.getTasks();
        boolean isTaskExist = tasks.contains(task);

        if (!isTaskExist) { //TODO: don't I need to write it like this? (!isTaskExist)?
            throw new ToDoException(ErrorMessage.TASK_DOESNT_BELONG_TO_CUSTOMER);
        }
        taskRepository.delete(task);
        tasks.remove(task);
        customerRepository.save(customer);//TODO: do I need this?
    }

    @Override
    public Task updateTask(long customerId, Task task) throws ToDoException {
        if (!taskRepository.existsById(task.getId())){
            throw new ToDoException(ErrorMessage.TASK_ID_NOT_FOUND);
        }

        if (!customerRepository.existsById((int) customerId)){
            throw new ToDoException(ErrorMessage.CUSTOMER_ID_NOT_FOUND);
        }
        Customer customer = customerRepository.findById(customerId);
        //TODO: the task that the user brings has an id = 0..
        Optional<Task> existingTask = taskRepository.findById(task.getId());
        Task taskToUpdate = existingTask.get();

        taskToUpdate.setCustomer(customer);
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setStartDate(task.getStartDate());
        taskToUpdate.setEndDate(task.getEndDate());
        taskToUpdate.setCategory(task.getCategory());

        return taskRepository.saveAndFlush(taskToUpdate);
    }

    @Override
    public List<Task> getCustomerTasks(long customerId) throws ToDoException {

        if (!customerRepository.existsById((int) customerId)){
            throw new ToDoException(ErrorMessage.CUSTOMER_ID_NOT_FOUND);
        }
        Customer customer = customerRepository.findById(customerId);
        customer.getTasks();
        return customerRepository.findCustomerTasksById(customerId);
    }

    @Override
    public List<Task> getCustomerTasksByCategory(long customerId, Category category) throws ToDoException {

        if (!customerRepository.existsById((int) customerId)){
            throw new ToDoException(ErrorMessage.CUSTOMER_ID_NOT_FOUND);
        }
        Customer customer = customerRepository.findById(customerId);
        //TODO: what to throw if the customer doesn't have tasks from this category?
        return customerRepository.findByCategoryAndCustomerId(customerId,category);
//        return customer.getTasks().stream()
//                .filter(task -> task.getCategory().equals(category))
//                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getCustomerTasksUntilEndDate(long customerId, LocalDate taskEndDate) throws ToDoException {

        if (!customerRepository.existsById((int) customerId)){
            throw new ToDoException(ErrorMessage.CUSTOMER_ID_NOT_FOUND);
        }

        if(taskEndDate.isBefore(LocalDate.now())){
            throw new ToDoException(ErrorMessage.END_DATE_ID_NOT_VALID);
        }

        Customer customer = customerRepository.findById(customerId);
        List<Task> tasksUntilDate = customer.getTasks().stream()
                .filter(task -> {
                    LocalDate endDate = task.getEndDate();
                    if (endDate == null) return false; // Assuming null means no end date
                    return !endDate.isAfter(taskEndDate);
                })
                .collect(Collectors.toList());

        if (tasksUntilDate.isEmpty()) {
            throw new ToDoException(ErrorMessage.NO_TASKS_AVAILABLE_UNTIL_END_DATE);
        }
        return customerRepository.findCustomerTasksBeforeEndDate(customerId,taskEndDate);
//        return tasksUntilDate;
    }

    @Override
    public List<Task> getCustomerTasksFromStartDate(long customerId, LocalDate startDate) throws ToDoException {

        if (!customerRepository.existsById((int) customerId)){
            throw new ToDoException(ErrorMessage.CUSTOMER_ID_NOT_FOUND);
        }

        if (startDate.isAfter(LocalDate.now())){
            throw new ToDoException(ErrorMessage.START_DATE_IS_AFTER_TODAY);
        }
        Customer customer = customerRepository.findById(customerId);
        return customerRepository.findCustomerTasksFromStartDate(customerId,startDate);
//        List<Task>tasksFromDate = customer.getTasks().stream().filter(task -> task.getStartDate() !=null && !task.getStartDate().isBefore(startDate))
//                .collect(Collectors.toList());
//        return tasksFromDate;
    }

    @Override
    public Customer getCustomerDetails(long customerId) throws ToDoException {

        if (!customerRepository.existsById((int) customerId)){
            throw new ToDoException(ErrorMessage.CUSTOMER_ID_NOT_FOUND);
        }

        return customerRepository.findById(customerId);
    }
}
