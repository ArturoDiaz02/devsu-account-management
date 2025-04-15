package co.devsu.bp.util.exception;

import org.springframework.http.HttpStatus;

public class NotBalanceException extends GeneralEntityException {
    public NotBalanceException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
