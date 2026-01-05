package com.bajaj.trading_sdk.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instrument {
    @Id
    private String symbol;
    private String exchange;
    private String instrumentType;
    private double lastTradedPrice;
}
