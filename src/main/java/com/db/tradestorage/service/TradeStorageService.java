package com.db.tradestorage.service;

import com.db.tradestorage.model.dto.TradeStorageDTO;

public interface TradeStorageService {

    void processTrade(TradeStorageDTO tradeStorageDTO);

    void updateExpiryFlag();
}
