package com.db.tradestorage.model.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "TradeStorage_HIS")
public class TradeStorageHistory {

    @Id
    @GeneratedValue(generator = "TRADE_STORAGE_HIS_SEQUENCE")
    @SequenceGenerator(name = "TRADE_STORAGE_HIS_SEQUENCE", sequenceName = "SEQ_TRADE_STORAGE_HIS_SEQUENCE_ID", allocationSize = 1)
    private Long id;

    @Column(name = "tradeId")
    private String tradeId;

    @Column(name = "tradeVersion")
    private int tradeVersion;

    @Column(name = "counterParty")
    private String counterParty;

    @Column(name = "bookId")
    private String bookId;

    @Column(name = "maturityDate")
    private LocalDate maturityDate;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    @Column(name = "expiredFlag")
    private String expiredFlag;

    @Column(name = "version")
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getExpiredFlag() {
        return expiredFlag;
    }

    public void setExpiredFlag(String expiredFlag) {
        this.expiredFlag = expiredFlag;
    }

    public int getTradeVersion() {
        return tradeVersion;
    }

    public void setTradeVersion(int tradeVersion) {
        this.tradeVersion = tradeVersion;
    }
}
