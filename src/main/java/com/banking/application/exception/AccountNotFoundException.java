package com.banking.application.exception;

public class AccountNotFoundException extends ApplicationException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
