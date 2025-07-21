package com.banking.application.port.in;

import com.banking.domain.model.Account;

import java.util.List;
import java.util.UUID;

public interface GetAccountUseCase {
    Account getById(UUID id);
    List<Account> getAll();
    List<Account> getByUserId(UUID userId);
}

