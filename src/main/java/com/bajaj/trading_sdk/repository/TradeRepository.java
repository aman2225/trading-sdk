package com.bajaj.trading_sdk.repository;

import com.bajaj.trading_sdk.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {}
