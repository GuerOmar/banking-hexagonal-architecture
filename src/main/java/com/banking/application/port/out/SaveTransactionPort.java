package com.banking.application.port.out;

import com.banking.domain.model.Transaction;

public interface SaveTransactionPort {
    Transaction save(Transaction transaction);
}