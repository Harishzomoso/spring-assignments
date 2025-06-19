package com.example.spring_demo;

import org.springframework.stereotype.Component;

@Component
public class CreditCardService implements PaymentService{
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " via Credit Card");
    }
}
