package com.banking.application.port.out;

import com.banking.domain.model.Account;

public interface SaveAccountPort {
    void save(Account account);
}
