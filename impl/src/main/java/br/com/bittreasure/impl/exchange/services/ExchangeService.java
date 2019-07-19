package br.com.bittreasure.impl.exchange.services;

import br.com.bittreasure.impl.exceptions.ApiException;
import br.com.bittreasure.impl.exceptions.errors.StandartError;
import br.com.bittreasure.impl.exceptions.issues.Issue;
import br.com.bittreasure.impl.exchange.models.Exchange;
import br.com.bittreasure.impl.exchange.repositories.ExchangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class ExchangeService {

    private final ExchangeRepository repository;

    public ExchangeService(ExchangeRepository repository) {
        this.repository = repository;
    }

    public Exchange find(String id) {
        return repository.findById(id).orElseThrow(() -> new ApiException(StandartError.builder()
                .name(HttpStatus.NOT_FOUND.name())
                .status(HttpStatus.NOT_FOUND.value())
                .message("No exchange with such id")
                .issue(new Issue(new NoSuchElementException("No exchange with id '" + id + "'")))
                .suggestedApplicationAction("User sent an invalid exchange id. Nothing to do")
                .suggestedUserAction("Verify the id and try again")
                .build()
        ));
    }

    public List<Exchange> findAll() {
        List<Exchange> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        list.forEach(e -> log.info(e.toString()));
        return list;
    }

    public Exchange save() {
        List<Exchange> exchanges = getExchanges();
        return repository.save(exchanges.get(0));
    }

    public List<Exchange> saveAll() {
        List<Exchange> exchanges = getExchanges();
        List<Exchange> returned = new ArrayList<>();
//        TODO trocar para adicionar todos ao inves de adicionar 5
        for (int i = 0; i < 5; i++) {
            returned.add(repository.save(exchanges.get(i)));
        }
//        repository.saveAll(exchanges);
        return returned;
    }

    private List<Exchange> getExchanges() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Exchange>> responseEntity = template
                .exchange("https://api.coinpaprika.com/v1/exchanges", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Exchange>>() {
                });
        return responseEntity.getBody();
    }

}
