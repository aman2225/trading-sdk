package com.bajaj.trading_sdk.controller;

import com.bajaj.trading_sdk.model.Trade;
import com.bajaj.trading_sdk.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trades")
@RequiredArgsConstructor
public class TradeController {

    private final TradeRepository repo;

    @GetMapping
    public List<Trade> all(){
        return repo.findAll();
    }
}
