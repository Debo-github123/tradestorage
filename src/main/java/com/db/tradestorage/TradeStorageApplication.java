package com.db.tradestorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradeStorageApplication {
    public static void main(String[] args) {

        SpringApplication.run(TradeStorageApplication.class, args);
    }
}
