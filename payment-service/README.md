# payment-service

DCEP microservice — Payment Processing, Fraud Detection, Transaction Status, Refunds

## Run

```bash
mvn spring-boot:run
```

Service starts on port **9418**.

## Base URL

```
http://46.250.226.123:9418/api/v1/payments
```

## Endpoints (POC — in-memory store)

| Method | Path                        | Description          |
|--------|-----------------------------|-----------------------|
| GET    | /api/v1/payments/health      | Health check          |
| GET    | /api/v1/payments             | List all Payment     |
| GET    | /api/v1/payments/{id}        | Get one by id         |
| POST   | /api/v1/payments             | Create                |
| PUT    | /api/v1/payments/{id}        | Update                |
| DELETE | /api/v1/payments/{id}        | Delete                |

Actuator health also available at `/actuator/health`.

> This is an MVP/POC scaffold: in-memory storage, no auth, no persistence.
> Next steps: swap `ConcurrentHashMap` store for Spring Data JPA + a real
> datasource (Azure SQL / Cosmos DB per the architecture diagram), add
> DTOs/mapping instead of exposing the entity directly, and wire in
> Spring Security once the Identity Provider is chosen.
