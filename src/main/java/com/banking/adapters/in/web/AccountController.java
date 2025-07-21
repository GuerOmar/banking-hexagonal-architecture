package com.banking.adapters.in.web;

import com.banking.adapters.in.web.dto.AccountRequest;
import com.banking.application.port.in.CreateAccountUseCase;
import com.banking.application.port.in.DepositUseCase;
import com.banking.application.port.in.GetAccountUseCase;
import com.banking.application.port.in.WithdrawUseCase;
import com.banking.domain.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountUseCase getAccountUseCase;
    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase, GetAccountUseCase getAccountUseCase, DepositUseCase depositUseCase, WithdrawUseCase withdrawUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAccountUseCase = getAccountUseCase;
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable UUID id) {
        return ResponseEntity.ok(getAccountUseCase.getById(id));
    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(getAccountUseCase.getAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Account>> getUserAccounts(@PathVariable UUID id) {
        return ResponseEntity.ok(getAccountUseCase.getByUserId(id));
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest) {
        Account account = createAccountUseCase.createAccount(accountRequest.userId(), accountRequest.amount());
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<Void> deposit(
            @PathVariable UUID accountId,
            @RequestBody AccountRequest accountRequest) {
        depositUseCase.deposit(accountId, accountRequest.amount());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<Void> withdraw(
            @PathVariable UUID accountId,
            @RequestBody AccountRequest accountRequest) {
        withdrawUseCase.withdraw(accountId, accountRequest.amount());
        return ResponseEntity.ok().build();
    }
}
