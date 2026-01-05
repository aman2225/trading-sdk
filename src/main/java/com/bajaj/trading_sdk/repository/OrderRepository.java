package com.bajaj.trading_sdk.repository;

import com.bajaj.trading_sdk.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {}
