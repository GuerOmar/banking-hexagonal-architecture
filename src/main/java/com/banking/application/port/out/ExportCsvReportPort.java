package com.banking.application.port.out;

import com.banking.domain.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface ExportCsvReportPort {
    void exportTransactions(List<Transaction> transactions, LocalDate date);
}
