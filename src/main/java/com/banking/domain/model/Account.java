package com.banking.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {
    private UUID id;
    private User owner;
    private BigDecimal balance;

    public Account(UUID id, User owner, BigDecimal balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
    }

    public UUID getId() { return id; }
    public User getOwner() { return owner; }
    public BigDecimal getBalance() { return balance; }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

