package com.example.spring_demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PayPalService implements PaymentService{
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " via PayPal");
    }
}
