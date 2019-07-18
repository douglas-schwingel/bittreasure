package br.com.bittreasure.impl.coin.filters;

import br.com.bittreasure.impl.exceptions.ApiException;
import br.com.bittreasure.impl.exceptions.issues.Issue;
import br.com.bittreasure.impl.exceptions.errors.StandartError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
        } catch (NullPointerException e) {
            log.info("Filter e/ou value vazio");
        }
    }
}
