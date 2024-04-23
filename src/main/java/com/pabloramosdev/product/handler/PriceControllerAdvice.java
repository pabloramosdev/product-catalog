package com.pabloramosdev.product.handler;

import com.pabloramosdev.product.exception.PriceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class PriceControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PriceNotFoundException.class})
    public ResponseEntity<Object> handlerPriceNotFound(PriceNotFoundException ex, WebRequest request) {
        log.warn(ex.getMessage());
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
    }

}
