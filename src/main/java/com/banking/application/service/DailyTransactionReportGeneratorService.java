package com.banking.application.service;

import com.banking.application.port.out.ExportCsvReportPort;
import com.banking.application.port.out.LoadTransactionPort;
import com.banking.domain.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DailyTransactionReportGeneratorService {

    private final LoadTransactionPort loadTransactionPort;
    private final ExportCsvReportPort exportCsvReportPort;

    public DailyTransactionReportGeneratorService(LoadTransactionPort loadTransactionPort, ExportCsvReportPort exportCsvReportPort) {
        this.loadTransactionPort = loadTransactionPort;
        this.exportCsvReportPort = exportCsvReportPort;
    }

    public void generateYesterdayReport() {
        LocalDate date = LocalDate.now().minusDays(1);
        List<Transaction> transactions = loadTransactionPort.loadByDate(date);
        exportCsvReportPort.exportTransactions(transactions, date);
    }
}
