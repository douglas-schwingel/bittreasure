package br.com.bittreasure.impl.exceptions;

import br.com.bittreasure.impl.exceptions.errors.StandartError;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiException extends RuntimeException {

    @Getter
    private List<StandartError> errors;

    public ApiException(StandartError... errors) {
        this.errors = Arrays.asList(errors);
    }


}
