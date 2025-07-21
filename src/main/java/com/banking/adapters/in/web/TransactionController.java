package com.banking.adapters.in.web;

import com.banking.adapters.in.web.dto.TransferRequest;
import com.banking.application.port.in.GetTransactionHistoryUseCase;
import com.banking.application.port.in.TransferMoneyUseCase;
import com.banking.domain.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransferMoneyUseCase transferMoneyUseCase;
    private final GetTransactionHistoryUseCase getTransactionHistoryUseCase;

    public TransactionController(TransferMoneyUseCase transferMoneyUseCase, GetTransactionHistoryUseCase getTransactionHistoryUseCase) {
        this.transferMoneyUseCase = transferMoneyUseCase;
        this.getTransactionHistoryUseCase = getTransactionHistoryUseCase;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest request) {
        transferMoneyUseCase.transfer(request.fromAccountId(), request.toAccountId(), request.amount());
        return ResponseEntity.ok("Transfer successful");
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getByAccount(@PathVariable UUID accountId) {
        List<Transaction> transactions = getTransactionHistoryUseCase.getByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }

}
