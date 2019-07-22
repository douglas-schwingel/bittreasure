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

import java.util.*;

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
        List<Coin> errors = new ArrayList<>();
        coins.forEach(c -> {
            Coin coin = null;
            try {
                coin = CoinOperations.getAllCoinInformations(c);
            } catch (IndexOutOfBoundsException e) {
                log.error("Error trying to save {}: TimedOut", c.getId());
                errors.add(c);
                log.warn("Error adding {} coins", errors.size());
                return;
            }
            log.info("Saving coin {}", coin.getName());
            repository.save(coin);
        });
        while(!errors.isEmpty()) {
            errors.forEach(c -> {
                Coin coin = null;
                try {
                    coin = CoinOperations.getAllCoinInformations(c);
                } catch (IndexOutOfBoundsException e) {
                    log.error("Error trying to save {}: TimedOut", c.getId());
                    return;
                }
                log.info("Saving coin {}", coin.getName());
                repository.save(coin);
                errors.remove(c);
            });

        }

    }

    public void updateCoinExchange(String coinId, String exchangeId) {
        Coin baseCoin;
        try {
            baseCoin = repository.findById(coinId).orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException | NullPointerException e) {
            log.error("Coin {} not registered yet", coinId);
            return;
        }
        try {
            Set<String> exchanges = baseCoin.getExchanges();
            exchanges.add(exchangeId);
        } catch (NullPointerException e) {
            log.warn("{} doesn't have exchange Set. Creating and adding exchange {}", coinId, exchangeId);
            baseCoin.setExchanges(new HashSet<>());
            baseCoin.getExchanges().add(exchangeId);
            log.info("Added exchange");
        }
        repository.save(baseCoin);
        log.info("Added {} to {}'s exchanges set", exchangeId, baseCoin.getName());
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
