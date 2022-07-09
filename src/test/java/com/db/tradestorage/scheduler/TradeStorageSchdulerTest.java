package com.db.tradestorage.scheduler;

import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TradeStorageSchdulerTest {

    @SpyBean
    TradeStorageScheduler tradeStorageScheduler;

    @Test
    public void verifyschedulerexecuted() throws InterruptedException {
        Thread.sleep(100L);
        await()
                .atMost(Duration.ONE_SECOND)
                .untilAsserted(() -> verify(tradeStorageScheduler, atLeast(1)).updateExpiryFlagOfTrade());
    }
}
