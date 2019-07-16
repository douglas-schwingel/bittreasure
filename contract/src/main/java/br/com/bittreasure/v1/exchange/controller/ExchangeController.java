package br.com.bittreasure.v1.exchange.controller;

import br.com.bittreasure.v1.exchange.controller.facade.ExchangeControllerFacade;
import br.com.bittreasure.v1.exchange.models.Exchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/exchanges")
public class ExchangeController {

    private final ExchangeControllerFacade facade;

    public ExchangeController(ExchangeControllerFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public Exchange save() {
       return facade.save();
    }
}
