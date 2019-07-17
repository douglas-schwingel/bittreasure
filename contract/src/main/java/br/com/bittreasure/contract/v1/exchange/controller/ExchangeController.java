package br.com.bittreasure.contract.v1.exchange.controller;

import br.com.bittreasure.contract.v1.exchange.controller.facade.ExchangeControllerFacade;
import br.com.bittreasure.contract.v1.exchange.models.Exchange;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"v1", "Exchange"})
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
