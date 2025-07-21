package com.banking.adapters.in.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequest(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {}
