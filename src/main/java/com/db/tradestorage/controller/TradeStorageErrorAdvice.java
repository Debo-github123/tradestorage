package com.db.tradestorage.controller;

import com.db.tradestorage.exception.Error;
import com.db.tradestorage.exception.InvalidTradeException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TradeStorageErrorAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidTradeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> processInvalidTradeException(InvalidTradeException ex) {
        Error error = new Error();
        error.setCode(String.valueOf(ex.getErrorCode()));
        error.setDescription(ex.getErrorMessage());

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> processUnknownError(Exception ex) {
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
