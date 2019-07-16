package br.com.bittreasure.v1.exchange.facades;

import br.com.bittreasure.v1.exchange.models.Exchange;
import br.com.bittreasure.v1.exchange.services.ExchangeService;
import org.springframework.stereotype.Service;

@Service
public class ExchangeFacade {

    private final ExchangeService service;

    public ExchangeFacade(ExchangeService service) {
        this.service = service;
    }

    public Exchange save() {
        return service.save();
    }
}
