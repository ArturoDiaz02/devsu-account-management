package co.devsu.bp.util.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends GeneralEntityException {

    public AlreadyExistException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST);
    }

}
