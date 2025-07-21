package com.banking.application.port.out;

import com.banking.domain.model.User;

public interface SaveUserPort {
    void save(User user);
}
