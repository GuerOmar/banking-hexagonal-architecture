package com.banking.application.port.out;

import com.banking.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadUserPort {
    Optional<User> loadById(UUID userId);
    List<User> loadAll();
}
