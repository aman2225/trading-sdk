package com.bajaj.trading_sdk.repository;

import com.bajaj.trading_sdk.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<Instrument, String> {}
