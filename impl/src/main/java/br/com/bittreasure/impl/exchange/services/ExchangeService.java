package br.com.bittreasure.impl.exchange.services;

import br.com.bittreasure.impl.coin.services.CoinService;
import br.com.bittreasure.impl.exceptions.ApiException;
import br.com.bittreasure.impl.exceptions.errors.StandartError;
import br.com.bittreasure.impl.exceptions.issues.Issue;
import br.com.bittreasure.impl.exchange.models.Exchange;
import br.com.bittreasure.impl.exchange.models.Market;
import br.com.bittreasure.impl.exchange.repositories.ExchangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class ExchangeService {

    private final ExchangeRepository repository;
    private final CoinService service;

    public ExchangeService(ExchangeRepository repository, CoinService service) {
        this.repository = repository;
        this.service = service;
    }

    public List<Exchange> updateCoinExchanges() {
//        TODO reaviliar esse metodo
        log.info("Initialized uptade of coins to put exchanges");
        List<Exchange> exchanges = ExchangeOperations.getExchanges();
        exchanges.forEach(e -> e.setAllMarkets(ExchangeOperations.getExchangeMarkets(e.getId())));
        exchanges.forEach(e -> {
                    List<Market> exchangeMarkets = e.getAllMarkets();
                    exchangeMarkets.forEach(m -> {
                        service.updateCoinExchanges(m.getBaseCurrencyId(), m.getQuoteCurrencyId(), e.getId());
                        log.info("Added {} to {} and {}", e.getId(), m.getBaseCurrencyId(), m.getQuoteCurrencyId());
                        repository.save(e);
                        log.info("Save exchange {} with id {}", e.getName(), e.getId());
                    });
                }
        );
        return exchanges;
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
        List<Exchange> exchanges = ExchangeOperations.getExchanges();
        return repository.save(exchanges.get(0));
    }

    public List<Exchange> saveAll() {
        List<Exchange> exchanges = ExchangeOperations.getExchanges();
        List<Exchange> returned = new ArrayList<>();
//        TODO trocar para adicionar todos ao inves de adicionar 5
        for (int i = 0; i < 5; i++) {
            returned.add(repository.save(exchanges.get(i)));
        }
//        repository.saveAll(exchanges);
        return returned;
    }


}
