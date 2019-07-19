package br.com.bittreasure.impl.exchange.facades;

import br.com.bittreasure.impl.exchange.models.Exchange;
import br.com.bittreasure.impl.exchange.services.ExchangeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeFacadeImpl {

    private final ExchangeService service;

    public ExchangeFacadeImpl(ExchangeService service) {
        this.service = service;
    }

    public Exchange find(String id) {
        return service.find(id);
    }

    public List<Exchange> findAll() {
        return service.findAll();
    }

    public List<Exchange> saveAll() {
        return service.updateCoinExchanges();
    }


}
