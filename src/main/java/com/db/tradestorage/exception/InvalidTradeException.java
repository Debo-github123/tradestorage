package com.db.tradestorage.exception;

public class InvalidTradeException extends RuntimeException {

    private int errorCode;
    private String errorMessage;

    public InvalidTradeException(ErrorCodes errocodes) {
        this.errorCode = errocodes.getCode();
        this.errorMessage = errocodes.getMessage();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
