package com.db.tradestorage.scheduler;

import com.db.tradestorage.service.TradeStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TradeStorageScheduler {

    @Autowired
    private TradeStorageService tradeStorageService;

    @Scheduled(fixedRateString = "${flag.expiry.check.rate:50}")
    public void updateExpiryFlagOfTrade() {
        tradeStorageService.updateExpiryFlag();
    }

}
