package br.com.bittreasure.impl.exceptions.handlers;

import br.com.bittreasure.impl.exceptions.ApiException;
import br.com.bittreasure.impl.exceptions.errors.ResponseError;
import br.com.bittreasure.impl.exceptions.errors.StandartError;
import br.com.bittreasure.impl.exceptions.issues.Issue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers {

    private static final String PT_BR = "pt-BR";

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseError> apiException(ApiException exception, HttpServletRequest request) {
        return ResponseEntity.status(exception.getErrors().get(0).getStatus())
                .body(new ResponseError(request.getRequestURI(), PT_BR, exception.getErrors()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> exception(Exception exception, HttpServletRequest request) {
        ApiException apiException = new ApiException(StandartError.builder()
                .message("Unexcpected error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .name(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .issue(new Issue(exception))
                .suggestedUserAction("Just Run!!")
                .suggestedApplicationAction("Don't do anything.. It's not your fault")
                .build()
        );
        return ResponseEntity.status(apiException.getErrors().get(0).getStatus())
                .body(new ResponseError(request.getRequestURI(), PT_BR, apiException.getErrors()));
    }
}
