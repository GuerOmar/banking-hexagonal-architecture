package com.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BankingAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingAppApplication.class, args);
    }
}
