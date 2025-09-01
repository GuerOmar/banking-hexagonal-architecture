package com.banking.adapters.out.persistence;

import com.banking.adapters.out.persistence.entity.TransactionJpa;
import com.banking.adapters.out.persistence.repository.TransactionRepository;
import com.banking.application.port.out.LoadTransactionPort;
import com.banking.application.port.out.SaveTransactionPort;
import com.banking.domain.model.Transaction;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionPersistenceAdapter implements SaveTransactionPort, LoadTransactionPort {

    private final TransactionRepository repository;

    public TransactionPersistenceAdapter(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionJpa entity = new TransactionJpa(
                transaction.getFromAccountId(),
                transaction.getToAccountId(),
                transaction.getAmount(),
                transaction.getTimestamp()
        );
        repository.save(entity);
        return transaction;
    }

    @Override
    public List<Transaction> loadByAccountId(UUID accountId) {
        return repository.findByAccountId(accountId)
                .stream()
                .map(this::mapToTransaction)
                .toList();
    }

    @Override
    public List<Transaction> loadByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay().minusNanos(1);
        return repository.findByBetweenTwoDates(startOfDay, endOfDay)
                .stream()
                .map(this::mapToTransaction)
                .toList();
    }

    private Transaction mapToTransaction(TransactionJpa t) {
        return new Transaction(t.getId(), t.getFromAccountId(), t.getToAccountId(), t.getAmount(), t.getTimestamp());
    }
}
