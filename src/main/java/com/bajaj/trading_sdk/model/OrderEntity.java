package com.bajaj.trading_sdk.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String symbol;
    private String side;
    private String type;
    private int quantity;
    private double price;
    private String status;
}
