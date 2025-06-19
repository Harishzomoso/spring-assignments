package com.example.spring_demo;

import org.springframework.stereotype.Component;

@Component
public class CheckoutService {
    private final PaymentService paymentService;

    // Constructor Injection
    public CheckoutService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void checkout(double amount) {
        paymentService.pay(amount);
    }
}
