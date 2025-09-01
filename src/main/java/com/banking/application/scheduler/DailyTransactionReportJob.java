package com.banking.application.scheduler;

import com.banking.application.service.DailyTransactionReportGeneratorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyTransactionReportJob {

    private final DailyTransactionReportGeneratorService dailyTransactionReportGeneratorService;

    public DailyTransactionReportJob(DailyTransactionReportGeneratorService dailyTransactionReportGeneratorService) {
        this.dailyTransactionReportGeneratorService = dailyTransactionReportGeneratorService;
    }

    @Scheduled(cron = "0 15 0 * * *")
    public void generateDailyTransactionReport() {
        dailyTransactionReportGeneratorService.generateYesterdayReport();
    }
}
