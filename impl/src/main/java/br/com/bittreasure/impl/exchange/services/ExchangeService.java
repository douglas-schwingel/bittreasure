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
import org.springframework.stereotype.Service;

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

    public List<Exchange> getExchangesInformation() {
        return ExchangeOperations.getExchanges();
    }

    public void updateMarkets(List<Exchange> exchanges, CoinService coinService) {
        exchanges.forEach(e -> {
            List<Market> markets = ExchangeOperations.getExchangeMarkets(e.getId());
            e.setAllMarkets(markets);
            markets.forEach(m -> {
                log.info("Setting {} to {} and {}", e.getId(), m.getBaseCurrencyId(), m.getQuoteCurrencyId());
                coinService.updateCoinExchange(m.getBaseCurrencyId(), e.getId());
                coinService.updateCoinExchange(m.getQuoteCurrencyId(), e.getId());
            });
            repository.save(e);
            log.info("Saved exchange {}", e.getName());
        });
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
        return list;
    }

}
