# Banking Hexagonal Architecture

This project is a demonstration of applying the **Hexagonal Architecture** in a Spring Boot application.

## 🧩 About

The core idea of Hexagonal Architecture is to isolate the business logic from the external world through well-defined ports and adapters. This architectural style helps build applications that are easier to test, maintain, and evolve.

In this small application we kept things simple, so there is only one entry point into the application via the Web (REST APIs) and there is only one output via persisting data directly to the relational database.

This banking app showcases:
- User and Account management
- Deposit, Withdrawal, and Transfer transactions
- Transaction history
- Scheduled job for negative balance alerts

## 📁 Structure Overview

```
src/
└── main/
  ├── domain/
  ├── application/
  │ ├── port/
  │ │ ├── in/ 
  │ │ └── out/
  │ ├── scheduler/
  │ └── service/
  └── adapters/
    ├── in/ 
    └── out/
```

## 🚀 Running Locally
You can use Docker Compose to instantiate the PostgreSQL database:
```bash
docker-compose up -d
````
Then start the Spring Boot application by launching the **BankingAppApplication**.