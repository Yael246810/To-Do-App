package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//TODO: to fix update customer (add another condition).

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		System.out.println("Start");
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
		System.out.println("End");
	}
}
