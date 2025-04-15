package co.devsu.bp.util.exception;

import lombok.Getter;

@Getter
public class EntityWrapperException extends Exception {

    private final int statusCode;
    private final String errorBody;

    public EntityWrapperException(int statusCode, String errorBody) {
        this.statusCode = statusCode;
        this.errorBody = errorBody;
    }
}
