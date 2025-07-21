package com.banking.application.service;

import com.banking.application.exception.UserNotFoundException;
import com.banking.application.port.in.CreateUserUseCase;
import com.banking.application.port.in.GetUserUseCase;
import com.banking.application.port.out.LoadUserPort;
import com.banking.application.port.out.SaveUserPort;
import com.banking.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements CreateUserUseCase, GetUserUseCase {

    private final SaveUserPort saveUserPort;
    private final LoadUserPort loadUserPort;

    public UserService(SaveUserPort saveUserPort, LoadUserPort loadUserPort) {
        this.saveUserPort = saveUserPort;
        this.loadUserPort = loadUserPort;
    }

    @Override
    public User createUser(String name, String email) {
        User user = new User(UUID.randomUUID(), name, email);
        saveUserPort.save(user);
        return user;
    }

    @Override
    public User getById(UUID id) {
        return loadUserPort.loadById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

    }

    @Override
    public List<User> getAll() {
        return loadUserPort.loadAll();
    }
}
