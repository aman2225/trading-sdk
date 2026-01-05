package com.bajaj.trading_sdk.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {
    @Id
    private String symbol;
    private int quantity;
    private double averagePrice;
    private double currentValue;
}
