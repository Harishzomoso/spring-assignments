package com.example.spring_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);

				ApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);

				CheckoutService checkoutService = context.getBean(CheckoutService.class);
				checkoutService.checkout(250.0);
			}
		}
