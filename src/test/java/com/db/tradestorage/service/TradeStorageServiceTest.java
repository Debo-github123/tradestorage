package com.db.tradestorage.service;

import com.db.tradestorage.mapper.MapTradeStorage;
import com.db.tradestorage.model.dto.TradeStorageDTO;
import com.db.tradestorage.model.internal.TradeStorage;
import com.db.tradestorage.model.internal.TradeStorageHistory;
import com.db.tradestorage.repository.TradeStorageHisRepositroy;
import com.db.tradestorage.repository.TradeStorageRepository;
import com.db.tradestorage.service.impl.TradeStorageServiceImpl;
import com.db.tradestorage.validator.TradeStorageValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TradeStorageServiceTest {

    @Mock
    private TradeStorageRepository tradeStorageRepository;

    @Mock
    private TradeStorageHisRepositroy tradeStorageHisRepositroy;

    @Mock
    private TradeStorageValidator tradeStorageValidator;

    @InjectMocks
    private TradeStorageService tradeStorageService = new TradeStorageServiceImpl();


    @Test
    void verifyIfTradeNotExist() {
        //given
        TradeStorageDTO tradeStorageDTO = new TradeStorageDTO();
        tradeStorageDTO.setMaturityDate(LocalDate.now());
        tradeStorageDTO.setTradeId("T1");
        tradeStorageDTO.setBookId("B1");
        tradeStorageDTO.setCounterParty("CP-1");
        tradeStorageDTO.setVersion(1);
        Mockito.when(tradeStorageRepository.findById("T1")).thenReturn(Optional.empty());
        //when
        tradeStorageService.processTrade(tradeStorageDTO);

        //then
        verify(tradeStorageValidator, atLeast(1)).validateMaturityDate(tradeStorageDTO);
        verify(tradeStorageRepository, atLeast(1)).save(any(TradeStorage.class));
    }

    @Test
    void verifyTradeAlreadyExist() {

        //given
        TradeStorageDTO tradeStorageDTO = new TradeStorageDTO();
        tradeStorageDTO.setMaturityDate(LocalDate.of(2025, 8, 15));
        tradeStorageDTO.setTradeId("T2");
        tradeStorageDTO.setBookId("B2");
        tradeStorageDTO.setCounterParty("CP-2");
        tradeStorageDTO.setVersion(2);

        TradeStorage tradeStorage = MapTradeStorage.mapToTradeDAO(tradeStorageDTO);
        tradeStorage.setCreatedDate(LocalDate.of(2020, 8, 15));
        tradeStorage.setModifiedDate(LocalDate.now());

        Mockito.when(tradeStorageRepository.findById("T2")).thenReturn(java.util.Optional.of(tradeStorage));

        //when
        tradeStorageService.processTrade(tradeStorageDTO);

        //then
        verify(tradeStorageValidator, atLeast(1)).validateMaturityDate(tradeStorageDTO);
        verify(tradeStorageRepository, atLeast(1)).findById("T2");
        verify(tradeStorageValidator, atLeast(1)).validateVersion(2, 2);
        verify(tradeStorageHisRepositroy, atLeast(1)).saveAndFlush(any(TradeStorageHistory.class));
        verify(tradeStorageRepository, atLeast(1)).save(tradeStorage);

    }

}
