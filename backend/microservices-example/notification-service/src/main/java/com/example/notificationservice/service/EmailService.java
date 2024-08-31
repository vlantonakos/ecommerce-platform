package com.example.notificationservice.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendOrderConfirmation(String orderId, String userId) {
        // In a real application, this would fetch the user's email using the userId
        // and then send an actual email
        System.out.println("Sending order confirmation email for order " + orderId + " to user " + userId);
    }
}
