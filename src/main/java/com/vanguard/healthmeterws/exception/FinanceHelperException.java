package com.vanguard.healthmeterws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FinanceHelperException extends Exception {

    public FinanceHelperException(String message) {
        super(message);
    }
}
