package com.banking.adapters.out.persistence.repository;

import com.banking.adapters.out.persistence.entity.AccountJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountJpa, UUID> {
    @Query("SELECT ac FROM AccountJpa ac WHERE ac.owner.id = :userId")
    List<AccountJpa> findByUserId(@Param("userId") UUID userId);
}
