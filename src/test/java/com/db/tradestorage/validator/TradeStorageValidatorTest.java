package com.db.tradestorage.validator;

import com.db.tradestorage.exception.InvalidTradeException;
import com.db.tradestorage.model.dto.TradeStorageDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class TradeStorageValidatorTest {

    @Autowired
    TradeStorageValidator tradeStorageValidator;

    @Test
    void testValidateVersion() {
        int tradeVersion = 1;
        int oldTradeVersion = 2;

        InvalidTradeException ex = Assertions.assertThrows(InvalidTradeException.class, () -> {
            tradeStorageValidator.validateVersion(tradeVersion, oldTradeVersion);
        });

        Assertions.assertEquals("trade version is lower than current version", ex.getErrorMessage());
    }

    @Test
    void testValidateMaturityDate() {
        TradeStorageDTO tradeStorageDTO = new TradeStorageDTO();
        tradeStorageDTO.setMaturityDate(LocalDate.of(2020, 1, 8));

        InvalidTradeException ex = Assertions.assertThrows(InvalidTradeException.class, () -> {
            tradeStorageValidator.validateMaturityDate(tradeStorageDTO);
        });

        Assertions.assertEquals(100, ex.getErrorCode());
    }


}
