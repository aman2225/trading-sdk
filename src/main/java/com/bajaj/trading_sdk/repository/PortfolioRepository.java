package com.bajaj.trading_sdk.repository;

import com.bajaj.trading_sdk.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, String> {}
