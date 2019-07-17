package br.com.bittreasure.contract.v1.handlers;

import br.com.bittreasure.contract.v1.exceptions.ApiException;
import br.com.bittreasure.contract.v1.exceptions.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlers {

    private static final String PT_BR = "pt-BR";

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseError> apiException(ApiException exception, HttpServletRequest request) {
        return ResponseEntity.status(exception.getErrors().get(0).getStatus())
                .body(new ResponseError(request.getContextPath(), PT_BR, exception.getErrors()));
    }
}
