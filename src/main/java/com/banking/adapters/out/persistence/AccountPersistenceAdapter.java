package com.banking.adapters.out.persistence;

import com.banking.adapters.out.persistence.entity.AccountJpa;
import com.banking.adapters.out.persistence.entity.UserJpa;
import com.banking.adapters.out.persistence.repository.AccountRepository;
import com.banking.adapters.out.persistence.repository.UserRepository;
import com.banking.application.exception.UserNotFoundException;
import com.banking.application.port.out.LoadAccountPort;
import com.banking.application.port.out.SaveAccountPort;
import com.banking.domain.model.Account;
import com.banking.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AccountPersistenceAdapter implements SaveAccountPort, LoadAccountPort {

    private final AccountRepository accountRepo;
    private final UserRepository userRepo;

    public AccountPersistenceAdapter(AccountRepository accountRepo, UserRepository userRepo) {
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void save(Account account) {
        AccountJpa entity = new AccountJpa();
        entity.setBalance(account.getBalance());

        UUID ownerId = account.getOwner().getId();
        UserJpa owner = userRepo.findById(ownerId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + ownerId + " not found"));
        entity.setOwner(owner);

        accountRepo.save(entity);
    }

    @Override
    public Optional<Account> loadById(UUID id) {
        return accountRepo.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Account> loadAll() {
        return accountRepo.findAll()
                .stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public List<Account> loadByUserId(UUID userId) {
        return accountRepo.findByUserId(userId)
                .stream().map(this::mapToDomain).toList();
    }

    private Account mapToDomain(AccountJpa jpa) {
        return new Account(
                jpa.getId(),
                new User(jpa.getOwner().getId(), jpa.getOwner().getName(), jpa.getOwner().getEmail()),
                jpa.getBalance()
        );
    }

}

