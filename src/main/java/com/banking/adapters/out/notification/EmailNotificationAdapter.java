package com.banking.adapters.out.notification;

import com.banking.application.port.out.SendEmailNotificationPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmailNotificationAdapter implements SendEmailNotificationPort {

    @Override
    public void notifyTransactionFailed(UUID userId, String message) {
        // TODO add an email service.
        System.out.println("MAIL TO " + userId + " WITH MESSAGE :\n" + message);
    }
}
