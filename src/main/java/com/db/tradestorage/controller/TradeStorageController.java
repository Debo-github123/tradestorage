package com.db.tradestorage.controller;


import com.db.tradestorage.model.dto.TradeStorageDTO;
import com.db.tradestorage.service.TradeStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class TradeStorageController {

    private static final Logger log = LoggerFactory.getLogger(TradeStorageController.class);

    @Autowired
    private TradeStorageService tradeStorageService;

    @RequestMapping(value = "/trade", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> tradeValidateStore(@RequestBody @Valid TradeStorageDTO trade) {
      //  log.debug("processing trade {}", trade);
        tradeStorageService.processTrade(trade);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }
}
