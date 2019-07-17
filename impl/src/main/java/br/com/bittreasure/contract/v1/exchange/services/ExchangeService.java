package br.com.bittreasure.contract.v1.exchange.services;

import br.com.bittreasure.contract.v1.exchange.models.Exchange;
import br.com.bittreasure.contract.v1.exchange.repositories.ExchangeRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExchangeService {

    private final ExchangeRepository repository;

    public ExchangeService(ExchangeRepository repository) {
        this.repository = repository;
    }

    public Exchange save() {
        List<Exchange> exchanges = getExchanges();
        return repository.save(exchanges.get(0));
    }

    private List<Exchange> getExchanges() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Exchange>> responseEntity = template.exchange("https://api.coinpaprika.com/v1/exchanges", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Exchange>>() {
                });
        return responseEntity.getBody();
    }

}
