package br.com.bittreasure.impl.coin.services;

import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.exceptions.ApiException;
import br.com.bittreasure.impl.exceptions.errors.StandartError;
import br.com.bittreasure.impl.exceptions.issues.Issue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CoinOperations {

    private static final String ERROR_MESSAGE = "Too many requests to Coinpaprika. Limit: 10/sec";
    private static final String APPLICATION_ACTION = "Verify how many threads are calling the api at the same time.";
    private static final String USER_ACTION = "Try again later";
    private final Environment environment;
    private String apiUri;

    public CoinOperations(Environment env) {
        this.environment = env;
        setApiUri();
    }

    void setApiUri() {
        this.apiUri = environment.getProperty("api.getCoins");
    }

    public List<Coin> getCoins(RestTemplate template) {
        for (int i = 0; i < 5; i++) {
            try {
                ResponseEntity<List<Coin>> responseEntity = template.exchange(apiUri, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Coin>>() {
                        });
                return responseEntity.getBody();
            } catch (HttpClientErrorException e) {
                log.warn("Get coins: {}", e.getMessage());
                if (i == 4) throw getApiException(e);
            }
        }
        return Collections.emptyList();
    }

    public Coin getCoinInformation(String id, RestTemplate template) {
        for (int i = 0; i < 5; i++) {
            try {
                ResponseEntity<Coin> responseEntity = template.exchange(apiUri + id, HttpMethod.GET,
                    null, new ParameterizedTypeReference<Coin>() {
                    });
                return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
                log.warn("Get information: {}", e.getMessage());
                if (i == 4) throw getApiException(e);
            }
        }
        return null;
    }

    void setPriceInformations(Coin coin, RestTemplate template) {
            for (int i = 0; i < 5; i++) {
                try {
            ResponseEntity<Coin[]> responseEntity = template.exchange(
                    apiUri + coin.getId() + "/ohlcv/latest/", HttpMethod.GET,
                    null, new ParameterizedTypeReference<Coin[]>() {
                    });
            Coin body = Objects.requireNonNull(responseEntity.getBody())[0];
            setCoinInformations(coin, body);
        } catch (IndexOutOfBoundsException e) {
            log.warn("Error trying to add price to {}: Coin doesn't have a price", coin.getId());
        } catch (HttpClientErrorException | ResourceAccessException e) {
                    log.warn("Set price: {}", e.getMessage());
                    if (i == 4) throw getApiException(e);
                }
            }
    }

    private void setCoinInformations(Coin coin, Coin body) {
        coin.setTimeOpen(body.getTimeOpen());
        coin.setTimeClose(body.getTimeClose());
        coin.setOpen(body.getOpen());
        coin.setClose(body.getClose());
        coin.setHigh(body.getHigh());
        coin.setLow(body.getLow());
        coin.setVolume(body.getVolume());
        coin.setMarketCap(body.getMarketCap());
    }

    private ApiException getApiException(RestClientException e) {
        return new ApiException(StandartError.builder()
                .name(HttpStatus.TOO_MANY_REQUESTS.name())
                .status(HttpStatus.TOO_MANY_REQUESTS.value())
                .message(ERROR_MESSAGE)
                .issue(new Issue(e))
                .suggestedUserAction(USER_ACTION)
                .suggestedApplicationAction(APPLICATION_ACTION)
                .build());
    }
}
