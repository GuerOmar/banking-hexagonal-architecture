package com.banking.application.port.in;

import com.banking.domain.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface GetTransactionHistoryUseCase {
    List<Transaction> getByAccountId(UUID accountId);
}
