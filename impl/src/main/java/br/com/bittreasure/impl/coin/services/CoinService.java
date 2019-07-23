package br.com.bittreasure.impl.coin.services;

import br.com.bittreasure.impl.coin.filters.models.FilterType;
import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.repositories.CoinRepository;
import br.com.bittreasure.impl.exceptions.ApiException;
import br.com.bittreasure.impl.exceptions.errors.StandartError;
import br.com.bittreasure.impl.exceptions.issues.Issue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Semaphore;

@Slf4j
@Service
public class CoinService {

    private final CoinRepository repository;
    private CoinOperations operations;

    public CoinService(CoinRepository repository, CoinOperations operations) {
        this.repository = repository;
        this.operations = operations;
    }

    @Scheduled(fixedRate =  3000)
    public void save() {
        log.info("Initializing save method");
        List<Coin> coins = operations.getCoins();
        Semaphore semaphore = new Semaphore(3);
        coins.forEach(c -> {
            try {
                semaphore.acquire();
                new Thread(() -> {
                    Coin coin = operations.getCoinInformation(c.getId());
                    operations.setPriceInformations(coin);
                    log.info("Saving coin {}", coin.getName());
                    repository.save(coin);
                    semaphore.release();
                }, "Save coin").start();
            } catch (InterruptedException e) {
                log.error("InterruptedException: {}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        });
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


}
