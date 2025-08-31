package com.banking.application.port.out;

import java.util.UUID;

public interface SendEmailNotificationPort {
    void notifyTransactionFailed(UUID userId, String message);
}
