package br.com.bittreasure.contract.v1.exchange.controller.facade;

import br.com.bittreasure.impl.exchange.facades.ExchangeFacade;
import br.com.bittreasure.impl.exchange.models.Exchange;
import org.springframework.stereotype.Service;

@Service
public class ExchangeControllerFacade {

    private final ExchangeFacade facade;

    public ExchangeControllerFacade(ExchangeFacade facade) {
        this.facade = facade;
    }

    public Exchange save() {
        return facade.save();
    }

}
