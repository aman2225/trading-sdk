# Trading SDK – Mini Trading Engine (Spring Boot)

This project is a mini trading backend system built using Spring Boot.  
It simulates a basic stock trading engine with instruments, order placement, trade execution, and portfolio tracking.

The goal of this project is to demonstrate backend design, REST API development, JPA usage, and trading workflow simulation.

---

## Features

- Tradable instruments list (NSE stocks)
- Market & Limit order placement
- Buy and Sell execution
- Automatic trade generation
- Portfolio management with average price calculation
- H2 in-memory database
- RESTful APIs
- Clean layered architecture (Controller → Service → Repository)

---

## Tech Stack

- Java 21  
- Spring Boot  
- Spring Data JPA  
- H2 Database  
- Maven  
- Lombok  

---

## How to Run

### 1. Clone the project

```bash
git clone https://github.com/aman2225/trading-sdk.git
cd trading-sdk
```
2. Run application
```bash   
   mvn spring-boot:run
```
Server will start at:
```bash
   http://localhost:8080
```
### API Endpoints
### Get Instruments
```bash
   GET /api/v1/instruments
```
Returns list of tradable instruments.
### Place Order
```bash
   POST /api/v1/orders
```
### Request Body
```bash
   {
  "symbol": "TCS",
  "side": "BUY",
  "type": "MARKET",
  "quantity": 2,
  "price": 0
}
```
### Response
```bash
   {
  "id": 1,
  "symbol": "INFY",
  "side": "BUY",
  "type": "MARKET",
  "quantity": 10,
  "price": 0.0,
  "status": "EXECUTED"
}
```
### Get Order By ID
```bash
   GET /api/v1/orders/{id}
```
### View Trades
```bash
   GET /api/v1/trades
```
### View Portfolio
```bash
   GET /api/v1/portfolio
```
### Order States
| Status    | Meaning                           |
| --------- | --------------------------------- |
| NEW       | Order is created and validated    |
| PLACED    | Limit order waiting for execution |
| EXECUTED  | Order executed successfully       |
| CANCELLED | Order was cancelled by user       |

### Portfolio Logic
- BUY updates quantity and recalculates average price
- SELL reduces quantity
- Current value is updated using latest market price

### Database
H2 in-memory database is used.

Access console at:
```bash
   http://localhost:8080/h2-console
```
JDBC URL:
```bash
   jdbc:h2:mem:tradingdb
```
### Assumptions Made
- Single user trading system
- All orders are assumed valid once passed validation
- Market orders execute instantly
- Limit orders are not matched against an order book
- Trades update portfolio immediately
- No brokerage, taxes, or partial fills are simulated
- Data is stored in H2 in-memory DB (resets on restart)

