package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//TODO: override hash & equals?
//TODO: a problem in the thread with the customerId
// TODO: unitests - with Maor
// TODO: another thread to remove the completed tasks, configuration
//TODO: create uml to this project. and to upload it to Github

//TODO - updated: https://stackoverflow.com/questions/46083329/no-converter-found-capable-of-converting-from-type-to-type - this is the solution

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		System.out.println("Start");
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
		System.out.println("End");
	}
}
