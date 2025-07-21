package com.banking.application.port.out;

import com.banking.domain.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface LoadTransactionPort {
    List<Transaction> loadByAccountId(UUID accountId);
}
