package com.banking.application.port.out;

import com.banking.domain.model.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadAccountPort {
    Optional<Account> loadById(UUID accountId);
    List<Account> loadAll();
    List<Account> loadByUserId(UUID userId);
    List<Account> loadAllNegativeBalanceAccounts();
}
