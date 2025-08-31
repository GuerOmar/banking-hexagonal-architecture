package com.banking.application.service;

import com.banking.application.exception.AccountNotFoundException;
import com.banking.application.exception.ApplicationException;
import com.banking.application.exception.UserNotFoundException;
import com.banking.application.port.in.CreateAccountUseCase;
import com.banking.application.port.in.DepositUseCase;
import com.banking.application.port.in.GetAccountUseCase;
import com.banking.application.port.in.WithdrawUseCase;
import com.banking.application.port.out.LoadAccountPort;
import com.banking.application.port.out.LoadUserPort;
import com.banking.application.port.out.SaveAccountPort;
import com.banking.domain.model.Account;
import com.banking.domain.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService implements CreateAccountUseCase, GetAccountUseCase, DepositUseCase, WithdrawUseCase {

    private final SaveAccountPort saveAccountPort;
    private final LoadAccountPort loadAccountPort;
    private final LoadUserPort loadUserPort;

    public AccountService(SaveAccountPort saveAccountPort, LoadUserPort loadUserPort, LoadAccountPort loadAccountPort) {
        this.saveAccountPort = saveAccountPort;
        this.loadUserPort = loadUserPort;
        this.loadAccountPort = loadAccountPort;
    }

    @Override
    public Account createAccount(UUID userId, BigDecimal initialBalance) {
        User user = loadUserPort.loadById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        Account account = new Account(user, initialBalance);
        saveAccountPort.save(account);
        return account;
    }

    @Override
    public Account getById(UUID id) {
        return loadAccountPort.loadById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with ID " + id + " not found"));
    }

    @Override
    public List<Account> getAll() {
        return loadAccountPort.loadAll();
    }

    @Override
    public List<Account> getByUserId(UUID userId) {
        return loadAccountPort.loadByUserId(userId);
    }

    @Override
    public void deposit(UUID accountId, BigDecimal amount) {
        Account account = loadAccountPort.loadById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account with ID " + accountId + " not found"));

        account.setBalance(account.getBalance().add(amount));
        saveAccountPort.save(account);
    }

    @Override
    public void withdraw(UUID accountId, BigDecimal amount) {
        Account account = loadAccountPort.loadById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account with ID " + accountId + " not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new ApplicationException("Insufficient balance for account " + accountId);
        }

        account.setBalance(account.getBalance().subtract(amount));
        saveAccountPort.save(account);
    }
}

