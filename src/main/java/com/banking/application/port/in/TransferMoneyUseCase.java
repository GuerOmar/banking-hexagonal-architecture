package com.banking.application.port.in;

import java.util.UUID;
import java.math.BigDecimal;

public interface TransferMoneyUseCase {
    void transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount);
}
