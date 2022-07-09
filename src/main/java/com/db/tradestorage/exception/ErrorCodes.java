package com.db.tradestorage.exception;

public enum ErrorCodes {

    MATURITY_DATE_EXPIRED(100, "Maturity Date is expired"),
    INVALID_VESION(200, "trade version is lower than current version");

    private final int code;
    private final String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
