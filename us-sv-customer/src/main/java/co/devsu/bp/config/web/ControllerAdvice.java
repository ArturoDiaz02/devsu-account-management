package co.devsu.bp.config.web;

import co.devsu.bp.util.exception.GeneralEntityException;
import co.devsu.bp.util.exception.wrapper.ExceptionEntityWrapper;
import co.devsu.bp.util.exception.constant.GenericErrors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvice {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionEntityWrapper> handleInternalServerError(Exception ex) {
        log.error("Exception handled as Internal server error: {}", ex.getMessage());
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ExceptionEntityWrapper.builder()
                .code(GenericErrors.GEN_ALL_01.name())
                .message(GenericErrors.GEN_ALL_01.getMessage())
                .build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionEntityWrapper> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionEntityWrapper.builder().build().buildFromError(ex));
    }

    @ExceptionHandler({GeneralEntityException.class})
    public ResponseEntity<ExceptionEntityWrapper> handleGeneralUnprocessableEntity(GeneralEntityException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ExceptionEntityWrapper.builder()
            .code(ex.getCode())
            .message(ex.getMessage())
            .build());

    }
}
