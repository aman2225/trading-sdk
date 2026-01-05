package com.bajaj.trading_sdk.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
    @Id
    @GeneratedValue
    private Long id;
    private Long orderId;
    private String symbol;
    private int quantity;
    private double price;
}
