package co.devsu.bp.util.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public abstract class GeneralEntityException extends Exception {
    private final String code;
    private final String message;
    private HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

    public GeneralEntityException(String message, String code) {
        log.warn(message);
        this.code = code;
        this.message = message;
    }

    public GeneralEntityException(String message, HttpStatus status) {
        log.warn(message);
        this.code = null;
        this.status = status;
        this.message = message;
    }

    public GeneralEntityException(String message) {
        log.warn(message);
        this.code = null;
        this.message = message;
    }
}
