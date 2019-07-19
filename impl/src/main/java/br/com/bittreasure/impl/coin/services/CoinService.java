package br.com.bittreasure.impl.coin.services;

import br.com.bittreasure.impl.coin.filters.models.FilterType;
import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.repositories.CoinRepository;
import br.com.bittreasure.impl.exceptions.ApiException;
import br.com.bittreasure.impl.exceptions.errors.StandartError;
import br.com.bittreasure.impl.exceptions.issues.Issue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        return repository.findById(id).orElseThrow(() -> new ApiException(StandartError.builder()
                .message("No coin with this id")
                .status(HttpStatus.BAD_REQUEST.value())
                .name(HttpStatus.BAD_REQUEST.name())
                .issue(new Issue(new IllegalArgumentException("No coin with the id '" + id + "'")))
                .suggestedUserAction("Please, verify the id and try again")
                .suggestedApplicationAction("Punch the stupid user")
                .build()));
    }

    public List<Coin> findWithFilters(FilterType filterType, String value) {
        return filterType.doSearch(repository, value);
    }

    public List<Coin> saveAll() {
//        TODO fazer salvar todos ao inves de salvar 5
        List<Coin> coins = getCoins();
        List<Coin> returned = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            returned.add(repository.save(coins.get(new Random().nextInt(coins.size()))));
        }

        return returned;
    }

    private List<Coin> getCoins() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Coin>> responseEntity = template.exchange("https://api.coinpaprika.com/v1/coins", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Coin>>() {
                });
        return responseEntity.getBody();
    }
}
