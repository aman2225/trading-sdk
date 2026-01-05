package com.bajaj.trading_sdk.service;

import com.bajaj.trading_sdk.model.*;
import com.bajaj.trading_sdk.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepo;
    private final TradeRepository tradeRepo;
    private final PortfolioRepository portfolioRepo;
    private final InstrumentRepository instrumentRepo;

    // PLACE ORDER
    public OrderEntity placeOrder(OrderEntity o) {

        log.info("Placing order: {}", o);

        if (o.getQuantity() <= 0)
            throw new RuntimeException("Quantity must be greater than 0");

        if (o.getType().equalsIgnoreCase("LIMIT") && o.getPrice() <= 0)
            throw new RuntimeException("Price required for LIMIT order");

        o.setStatus("NEW");
        orderRepo.save(o);
        log.info("Order {} saved with NEW status", o.getId());

        if (o.getType().equalsIgnoreCase("MARKET")) {
            log.info("Executing MARKET order {}", o.getId());
            execute(o);
        } else {
            o.setStatus("PLACED");
            log.info("LIMIT order {} placed", o.getId());
        }

        return orderRepo.save(o);
    }

    // EXECUTION
    private void execute(OrderEntity o) {

        log.info("Executing order {}", o.getId());

        Instrument i = instrumentRepo.findById(o.getSymbol())
                .orElseThrow(() -> new RuntimeException("Instrument not found: " + o.getSymbol()));

        double execPrice = o.getType().equalsIgnoreCase("MARKET")
                ? i.getLastTradedPrice()
                : o.getPrice();

        o.setStatus("EXECUTED");

        Trade t = new Trade();
        t.setOrderId(o.getId());
        t.setSymbol(o.getSymbol());
        t.setQuantity(o.getQuantity());
        t.setPrice(execPrice);
        tradeRepo.save(t);
        log.info("Trade created for order {}", o.getId());

        Portfolio p = portfolioRepo.findById(o.getSymbol()).orElse(new Portfolio());

        if (o.getSide().equalsIgnoreCase("BUY")) {
            int total = p.getQuantity() + o.getQuantity();
            double avg = ((p.getQuantity() * p.getAveragePrice()) +
                    (o.getQuantity() * execPrice)) / total;

            p.setQuantity(total);
            p.setAveragePrice(avg);
            log.info("BUY updated portfolio {} qty {}", o.getSymbol(), total);
        } else {
            if (p.getQuantity() < o.getQuantity())
                throw new RuntimeException("Not enough quantity to SELL");

            p.setQuantity(p.getQuantity() - o.getQuantity());
            log.info("SELL updated portfolio {} qty {}", o.getSymbol(), p.getQuantity());
        }

        p.setSymbol(o.getSymbol());
        p.setCurrentValue(p.getQuantity() * i.getLastTradedPrice());
        portfolioRepo.save(p);
    }

    // CANCEL ORDER
    public OrderEntity cancelOrder(Long id) {

        log.info("Cancel request for order {}", id);

        OrderEntity o = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));

        if (o.getStatus().equalsIgnoreCase("EXECUTED"))
            throw new RuntimeException("Executed orders cannot be cancelled");

        if (o.getStatus().equalsIgnoreCase("CANCELLED"))
            throw new RuntimeException("Order already cancelled");

        o.setStatus("CANCELLED");
        log.info("Order {} cancelled", id);
        return orderRepo.save(o);
    }

    // GET ORDER
    public OrderEntity getOrder(Long id) {
        log.info("Fetching order {}", id);
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }
}
