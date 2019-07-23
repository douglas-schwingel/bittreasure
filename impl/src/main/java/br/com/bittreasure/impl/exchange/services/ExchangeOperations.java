package br.com.bittreasure.impl.exchange.services;

import br.com.bittreasure.impl.exchange.models.Exchange;
import br.com.bittreasure.impl.exchange.models.Market;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ExchangeOperations {

    private ExchangeOperations() {

    }

    static List<Exchange> getExchanges() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Exchange>> responseEntity = template
                .exchange("https://api.coinpaprika.com/v1/exchanges", HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Exchange>>() {
                        });
        return responseEntity.getBody();
    }

    static List<Market> getExchangeMarkets(String id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Market>> responseEntity = template
                .exchange("https://api.coinpaprika.com/v1/exchanges/" + id + "/markets", HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Market>>() {
                        });
        return responseEntity.getBody();
    }
}
