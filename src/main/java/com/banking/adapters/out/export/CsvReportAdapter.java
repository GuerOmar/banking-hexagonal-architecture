package com.banking.adapters.out.export;

import com.banking.application.port.out.ExportCsvReportPort;
import com.banking.domain.model.Transaction;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@Component
public class CsvReportAdapter implements ExportCsvReportPort {

    @Override
    public void exportTransactions(List<Transaction> transactions, LocalDate date) {
        String fileName = "transactions-" + date + ".csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("id,fromAccountId,toAccountId,amount,timestamp");

            for (Transaction tx : transactions) {
                writer.printf("%s,%s,%s,%s,%s\n",
                        tx.getId(),
                        tx.getFromAccountId(),
                        tx.getToAccountId(),
                        tx.getAmount(),
                        tx.getTimestamp()
                );
            }

            System.out.println("[CSV REPORT] Generated Csv report file: " + fileName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to write report file", e);
        }
    }
}
