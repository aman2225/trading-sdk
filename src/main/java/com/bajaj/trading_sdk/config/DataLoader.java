package com.bajaj.trading_sdk.config;

import com.bajaj.trading_sdk.model.Instrument;
import com.bajaj.trading_sdk.repository.InstrumentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final InstrumentRepository repo;

    @PostConstruct
    public void load() {
        repo.save(new Instrument("INFY","NSE","EQUITY",1500));
        repo.save(new Instrument("TCS","NSE","EQUITY",3400));
        repo.save(new Instrument("RELIANCE","NSE","EQUITY",2500));
    }
}
