package com.banking.adapters.out.persistence;

import com.banking.adapters.out.persistence.entity.UserJpa;
import com.banking.adapters.out.persistence.repository.UserRepository;
import com.banking.application.port.out.LoadUserPort;
import com.banking.application.port.out.SaveUserPort;
import com.banking.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserPersistenceAdapter implements SaveUserPort, LoadUserPort {

    private final UserRepository repository;

    public UserPersistenceAdapter(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        UserJpa entity = new UserJpa();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        repository.save(entity);
    }

    @Override
    public Optional<User> loadById(UUID userId) {
        return repository.findById(userId)
                .map(this::mapToDomain);
    }

    @Override
    public List<User> loadAll() {
        return repository.findAll()
                .stream().map(this::mapToDomain).toList();
    }

    private User mapToDomain(UserJpa jpa) {
        return new User(jpa.getId(), jpa.getName(), jpa.getEmail());
    }
}
