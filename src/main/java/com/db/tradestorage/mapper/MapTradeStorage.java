package com.db.tradestorage.mapper;

import com.db.tradestorage.model.dto.TradeStorageDTO;
import com.db.tradestorage.model.internal.TradeStorage;

public class MapTradeStorage {

    public static TradeStorage mapToTradeDAO(TradeStorageDTO tradeStorageDTO) {
        TradeStorage tradeStorage = new TradeStorage();
        tradeStorage.setTradeId(tradeStorageDTO.getTradeId());
        tradeStorage.setBookId(tradeStorageDTO.getBookId());
        tradeStorage.setCounterParty(tradeStorageDTO.getCounterParty());
        tradeStorage.setMaturityDate(tradeStorageDTO.getMaturityDate());
        tradeStorage.setVersion(tradeStorageDTO.getVersion());

        return tradeStorage;
    }

    public static TradeStorage mapToTradeDTO(TradeStorage tradeStorage) {
        TradeStorage tradeStorageDTO = new TradeStorage();
        tradeStorageDTO.setTradeId(tradeStorage.getTradeId());
        tradeStorageDTO.setBookId(tradeStorage.getBookId());
        tradeStorageDTO.setCounterParty(tradeStorage.getCounterParty());
        tradeStorageDTO.setMaturityDate(tradeStorage.getMaturityDate());
        tradeStorageDTO.setExpiredFlag(tradeStorage.getExpiredFlag());
        tradeStorageDTO.setVersion(tradeStorage.getVersion());

        return tradeStorageDTO;
    }
}
