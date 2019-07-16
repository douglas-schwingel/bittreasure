package br.com.bittreasure.teste;

import br.com.bittreasure.coin.models.Coin;
import br.com.bittreasure.coin.repositories.CoinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class Teste {

    private CoinRepository repository;

    public Teste(CoinRepository repository) {
        this.repository = repository;
    }

    public Coin run() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Coin>> exchange = template.exchange("https://api.coinpaprika.com/v1/coins", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Coin>>() {
                });
        List<Coin> body = exchange.getBody();
        log.info(body.get(1).getId());
        return repository.save(body.get(1));
    }

    public Coin find(String id) {
        return repository.findById(id).get();
    }

    public List<Coin> findAll() {
        List<Coin> list = new ArrayList<>();
        repository.findAll().forEach(c -> list.add(c));
        return list;
    }

}
