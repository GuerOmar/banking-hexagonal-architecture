package com.banking.application.port.in;

import com.banking.domain.model.User;

public interface CreateUserUseCase {
    User createUser(String name, String email);
}
