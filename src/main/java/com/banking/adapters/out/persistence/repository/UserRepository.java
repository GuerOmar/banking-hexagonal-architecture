package com.banking.adapters.out.persistence.repository;

import com.banking.adapters.out.persistence.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserJpa, UUID> {
}
