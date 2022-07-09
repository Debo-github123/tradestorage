package com.db.tradestorage.controller;

import com.db.tradestorage.model.dto.TradeStorageDTO;
import com.db.tradestorage.service.TradeStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TradeStorageController.class)

public class TradeStorageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    TradeStorageService tradeStorageService;


    @Test
    void testTradeValidateStore() throws Exception {

        TradeStorageDTO tradeStorageDTO = new TradeStorageDTO();
        tradeStorageDTO.setTradeId("T1");
        tradeStorageDTO.setVersion(2);
        tradeStorageDTO.setCounterParty("CP1");
        tradeStorageDTO.setBookId("B1");
        tradeStorageDTO.setMaturityDate(LocalDate.of(2022, 12,8));


        final MockHttpServletRequestBuilder request = post("/trade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(tradeStorageDTO));

        mockMvc.perform(request) .andReturn();

    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
