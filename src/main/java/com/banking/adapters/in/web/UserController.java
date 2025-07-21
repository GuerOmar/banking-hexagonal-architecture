package com.banking.adapters.in.web;

import com.banking.adapters.in.web.dto.UserRequest;
import com.banking.application.port.in.CreateUserUseCase;
import com.banking.application.port.in.GetUserUseCase;
import com.banking.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, GetUserUseCase getUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok(getUserUseCase.getById(id));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(getUserUseCase.getAll());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        User user = createUserUseCase.createUser(userRequest.name(), userRequest.email());
        return ResponseEntity.ok(user);
    }
}
