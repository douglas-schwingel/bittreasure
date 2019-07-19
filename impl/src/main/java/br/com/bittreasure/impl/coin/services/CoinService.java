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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class CoinService {

    private final CoinRepository repository;

    public CoinService(CoinRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 3000)
    public void save() {
        log.info("Initializing save method");
        List<Coin> coins = CoinOperations.getCoins();
        coins.forEach(c -> {
            Coin coin = null;
            try {
                coin = CoinOperations.getAllCoinInformations(c);
            } catch (IndexOutOfBoundsException e) {
                log.error("Error trying to save {}: TimedOut", c.getId());
                return;
            }
            log.info("Saving coin {}", coin.getName());
            repository.save(coin);
        });
    }

    public void updateCoinExchanges(String baseCoinId, String quoteCoinId, String exchangeId) {
        Coin baseCoin = repository.findById(baseCoinId).get();
        Coin quoteCoin = repository.findById(quoteCoinId).get();
        baseCoin.getExchanges().add(exchangeId);
        quoteCoin.getExchanges().add(exchangeId);
        repository.save(baseCoin);
        repository.save(quoteCoin);
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
        List<Coin> coins = CoinOperations.getCoins();
        List<Coin> returned = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            returned.add(repository.save(coins.get(new Random().nextInt(coins.size()))));
        }

        return returned;
    }

}
