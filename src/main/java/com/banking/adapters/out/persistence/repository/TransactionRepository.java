package com.banking.adapters.out.persistence.repository;

import com.banking.adapters.out.persistence.entity.TransactionJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionJpa, UUID> {
    @Query("SELECT t FROM TransactionJpa t WHERE t.fromAccountId = :accountId OR t.toAccountId = :accountId ORDER BY t.timestamp DESC")
    List<TransactionJpa> findByAccountId(@Param("accountId") UUID accountId);
}
