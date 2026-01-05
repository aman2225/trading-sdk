package com.bajaj.trading_sdk.controller;

import com.bajaj.trading_sdk.model.Instrument;
import com.bajaj.trading_sdk.repository.InstrumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instruments")
@RequiredArgsConstructor
public class InstrumentController {

    private final InstrumentRepository repo;

    @GetMapping
    public List<Instrument> getAll(){
        return repo.findAll();
    }
}
