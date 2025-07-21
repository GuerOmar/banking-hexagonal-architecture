package com.banking.application.service;

import com.banking.application.exception.AccountNotFoundException;
import com.banking.application.exception.ApplicationException;
import com.banking.application.port.in.GetTransactionHistoryUseCase;
import com.banking.application.port.in.TransferMoneyUseCase;
import com.banking.application.port.out.LoadAccountPort;
import com.banking.application.port.out.LoadTransactionPort;
import com.banking.application.port.out.SaveAccountPort;
import com.banking.application.port.out.SaveTransactionPort;
import com.banking.domain.model.Account;
import com.banking.domain.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService implements TransferMoneyUseCase, GetTransactionHistoryUseCase {
    private final SaveAccountPort saveAccountPort;
    private final LoadAccountPort loadAccountPort;
    private final SaveTransactionPort saveTransactionPort;
    private final LoadTransactionPort loadTransactionPort;

    public TransactionService(SaveAccountPort saveAccountPortository, LoadAccountPort loadAccountPort, SaveTransactionPort saveTransactionPort, LoadTransactionPort loadTransactionPort) {
        this.saveAccountPort = saveAccountPortository;
        this.loadAccountPort = loadAccountPort;
        this.saveTransactionPort = saveTransactionPort;
        this.loadTransactionPort = loadTransactionPort;
    }

    @Transactional
    @Override
    public void transfer(UUID fromId, UUID toId, BigDecimal amount) {
        Account from = loadAccountPort.loadById(fromId)
                .orElseThrow(() -> new AccountNotFoundException("Source account with ID " + fromId + " not found"));
        Account to = loadAccountPort.loadById(toId)
                .orElseThrow(() -> new AccountNotFoundException("Target account with ID " + toId + " not found"));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new ApplicationException("Insufficient balance for account " + fromId);
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        saveAccountPort.save(from);
        saveAccountPort.save(to);

        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                fromId,
                toId,
                amount,
                LocalDateTime.now()
        );
        saveTransactionPort.save(transaction);
    }

    @Override
    public List<Transaction> getByAccountId(UUID accountId) {
        return loadTransactionPort.loadByAccountId(accountId);
    }
}
