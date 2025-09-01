package com.banking.application.scheduler;

import com.banking.application.port.out.LoadAccountPort;
import com.banking.domain.model.Account;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class NegativeBalanceAlertJob {

    private final LoadAccountPort loadAccountPort;

    public NegativeBalanceAlertJob(LoadAccountPort loadAccountPort) {
        this.loadAccountPort = loadAccountPort;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void checkNegativeBalances() {
        List<Account> accounts = loadAccountPort.loadAllNegativeBalanceAccounts();

        accounts.forEach(acc -> System.out.println(
                        "Account " + acc.getId() + " has negative balance : " + acc.getBalance()));
    }
}
