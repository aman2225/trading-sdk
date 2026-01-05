package com.bajaj.trading_sdk.controller;

import com.bajaj.trading_sdk.model.Portfolio;
import com.bajaj.trading_sdk.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioRepository repo;

    @GetMapping
    public List<Portfolio> all(){
        return repo.findAll();
    }
}
