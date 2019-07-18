package br.com.bittreasure.impl.exchange.facades;

import br.com.bittreasure.impl.exchange.models.Exchange;
import br.com.bittreasure.impl.exchange.services.ExchangeService;
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
