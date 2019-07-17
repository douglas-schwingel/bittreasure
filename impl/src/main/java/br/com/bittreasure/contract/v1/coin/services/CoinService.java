package br.com.bittreasure.contract.v1.coin.services;

import br.com.bittreasure.contract.v1.coin.models.Coin;
import br.com.bittreasure.contract.v1.coin.repositories.CoinRepository;
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
public class CoinService {

    private CoinRepository repository;

    public CoinService(CoinRepository repository) {
        this.repository = repository;
    }

    public Coin save() {
        List<Coin> coins = getCoins();
        return repository.save(coins.get(1));
    }

    public Coin find(String id) {
//        TODO decorator
        return repository.findById(id).get();
    }

    public Coin teste() {
        Coin coin = new Coin();
        coin.setId("aphelion");
        coin.setName("Aphelion");
        return repository.save(coin);
    }

    public List<Coin> findAll() {
        log.info("Entrou no findAll do service");
        List<Coin> list = new ArrayList<>();
        log.info(repository.findAll().toString());
        repository.findAll().forEach(list::add);
        list.forEach(c -> log.info(c.getName()));
        return list;
    }

    private List<Coin> getCoins() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Coin>> responseEntity = template.exchange("https://api.coinpaprika.com/v1/coins", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Coin>>() {
                });
        return responseEntity.getBody();
    }
}
