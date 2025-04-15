package co.devsu.bp.util.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends GeneralEntityException {

    public NotFoundException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST);
    }

}
