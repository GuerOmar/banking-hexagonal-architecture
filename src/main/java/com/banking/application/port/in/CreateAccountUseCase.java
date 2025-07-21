package com.banking.application.port.in;

import com.banking.domain.model.Account;

import java.math.BigDecimal;
import java.util.UUID;

public interface CreateAccountUseCase {
    Account createAccount(UUID userId, BigDecimal initialBalance);
}
