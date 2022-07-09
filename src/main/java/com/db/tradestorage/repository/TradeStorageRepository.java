package com.db.tradestorage.repository;

import com.db.tradestorage.model.internal.TradeStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeStorageRepository extends JpaRepository<TradeStorage, String> {
}
