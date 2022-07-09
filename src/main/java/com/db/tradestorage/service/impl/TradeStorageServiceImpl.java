package com.db.tradestorage.service.impl;

import com.db.tradestorage.mapper.MapTradeStorage;
import com.db.tradestorage.model.dto.TradeStorageDTO;
import com.db.tradestorage.model.internal.TradeStorage;
import com.db.tradestorage.model.internal.TradeStorageHistory;
import com.db.tradestorage.repository.TradeStorageHisRepositroy;
import com.db.tradestorage.repository.TradeStorageRepository;
import com.db.tradestorage.service.TradeStorageService;
import com.db.tradestorage.validator.TradeStorageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TradeStorageServiceImpl implements TradeStorageService {

    @Autowired
    TradeStorageRepository tradeStorageRepository;

    @Autowired
    private TradeStorageValidator tradeStorageValidator;

    @Autowired
    private TradeStorageHisRepositroy tradeStorageHisRepositroy;


    @Override
    public void processTrade(TradeStorageDTO tradeStorageDTO) {
        tradeStorageValidator.validateMaturityDate(tradeStorageDTO);
        TradeStorage existingTrade = getExistingTradeStorage(tradeStorageDTO.getTradeId());
        TradeStorage tradeStorage = MapTradeStorage.mapToTradeDAO(tradeStorageDTO);
        if (existingTrade == null) {
            saveTradeStorageData(tradeStorage);
        } else {
            tradeStorageValidator.validateVersion(tradeStorageDTO.getVersion(), existingTrade.getVersion());
            updateTradeStorage(existingTrade, tradeStorage);
        }

    }

    private void updateTradeStorage(TradeStorage existingTrade, TradeStorage newTrade) {
        existingTrade.setBookId(newTrade.getBookId());
        existingTrade.setVersion(newTrade.getVersion());
        existingTrade.setMaturityDate(newTrade.getMaturityDate());
        existingTrade.setCounterParty(newTrade.getCounterParty());
        existingTrade.setModifiedDate(LocalDate.now());
        //save to history
        updateTradeStroageHistory(existingTrade);
        tradeStorageRepository.save(existingTrade);
    }

    private void updateTradeStroageHistory(TradeStorage existingTrade) {
        TradeStorageHistory tradeStorageHistory = new TradeStorageHistory();
        tradeStorageHistory.setTradeId(existingTrade.getTradeId());
        tradeStorageHistory.setBookId(existingTrade.getBookId());
        tradeStorageHistory.setCounterParty(existingTrade.getCounterParty());
        tradeStorageHistory.setMaturityDate(existingTrade.getMaturityDate());
        tradeStorageHistory.setExpiredFlag(existingTrade.getExpiredFlag());
        tradeStorageHistory.setCreatedDate(existingTrade.getCreatedDate());
        tradeStorageHistory.setTradeVersion(existingTrade.getVersion());
        tradeStorageHistory.setVersion(existingTrade.getVersion() + 1);

        tradeStorageHisRepositroy.saveAndFlush(tradeStorageHistory);

    }


    private void saveTradeStorageData(TradeStorage tradeStorage) {
        tradeStorage.setCreatedDate(LocalDate.now());
        tradeStorage.setExpiredFlag("N");
        tradeStorageRepository.save(tradeStorage);
    }


    private TradeStorage getExistingTradeStorage(String tradeId) {
        Optional<TradeStorage> existingTrade = tradeStorageRepository.findById(tradeId);
        if (existingTrade != null && existingTrade.isPresent()) {
            return existingTrade.get();
        }
        return null;
    }

    @Override
    public void updateExpiryFlag() {
        tradeStorageRepository.findAll().stream().forEach(tradeStorage -> {
            if (tradeStorage.getMaturityDate().isBefore(LocalDate.now())) {
                tradeStorage.setExpiredFlag("Y");
                tradeStorageRepository.save(tradeStorage);
            }
        });

    }
}
