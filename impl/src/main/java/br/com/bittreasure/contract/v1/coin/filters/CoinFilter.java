package br.com.bittreasure.contract.v1.coin.filters;

import br.com.bittreasure.contract.v1.exceptions.ApiException;
import br.com.bittreasure.contract.v1.exceptions.Issue;
import br.com.bittreasure.contract.v1.exceptions.StandartError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CoinFilter {

    public void filtra(String filter, String value) {
        verificaSeEhValido(filter);

    }

    private void verificaSeEhValido(String filter) {
        try {
            ValidCoinFilters.valueOf(filter.toUpperCase());

        } catch (IllegalArgumentException e) {
            throw new ApiException(StandartError.builder()
                            .message("Invalid filter")
                            .status(HttpStatus.BAD_REQUEST.value())
                            .name(HttpStatus.BAD_REQUEST.name())
                            .issue(new Issue(e))
                            .suggestedUserAction("Please, verify the filter name and try again")
                            .suggestedApplicationAction("Don't do anything.. It's not your fault")
                            .build()
            );
        }
    }
}
