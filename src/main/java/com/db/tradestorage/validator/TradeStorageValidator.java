package com.db.tradestorage.validator;

import com.db.tradestorage.exception.ErrorCodes;
import com.db.tradestorage.exception.InvalidTradeException;
import com.db.tradestorage.model.dto.TradeStorageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TradeStorageValidator {


    private static final Logger log = LoggerFactory.getLogger(TradeStorageValidator.class);

    public void validateVersion(int newTradeVersion, int oldTradeVersion) {
        if (newTradeVersion < oldTradeVersion) {
            log.error("Trader version {} is lower than current version {}", newTradeVersion, oldTradeVersion);
            throw new InvalidTradeException(ErrorCodes.INVALID_VESION);
        }

    }

    public void validateMaturityDate(TradeStorageDTO trade) {
        if (trade.getMaturityDate().isBefore(LocalDate.now())) {
            log.error("maturity date {} already expired for trade id {}", trade.getMaturityDate(),
                    trade.getTradeId());
            throw new InvalidTradeException(ErrorCodes.MATURITY_DATE_EXPIRED);
        }


    }
}
