package com.banking.adapters.out.persistence.repository;

import com.banking.adapters.out.persistence.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserJpa, UUID> {
}
