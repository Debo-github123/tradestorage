package com.db.tradestorage.repository;

import com.db.tradestorage.model.internal.TradeStorage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class TradeStorageRepoTest {
    @Autowired
    TradeStorageRepository tradeStorageRepository;

    @Test
    void findByByTradeId() {
        TradeStorage tradeStorage = new TradeStorage();
        tradeStorage.setTradeId("T1");
        tradeStorage.setVersion(1);
        tradeStorage.setExpiredFlag("N");

        tradeStorageRepository.save(tradeStorage);
        Optional<TradeStorage> actualResult = tradeStorageRepository.findById("T1");

        assertThat(actualResult.isPresent()).isTrue();
    }

}
