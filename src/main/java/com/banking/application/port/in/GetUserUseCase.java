package com.banking.application.port.in;

import com.banking.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface GetUserUseCase {
    User getById(UUID id);
    List<User> getAll();
}
