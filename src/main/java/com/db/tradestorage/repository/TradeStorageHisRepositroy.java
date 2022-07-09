package com.db.tradestorage.repository;

import com.db.tradestorage.model.internal.TradeStorageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeStorageHisRepositroy extends JpaRepository<TradeStorageHistory, Long> {
}
