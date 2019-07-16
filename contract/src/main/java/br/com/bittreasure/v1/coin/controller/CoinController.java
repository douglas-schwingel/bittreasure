package br.com.bittreasure.v1.coin.controller;

import br.com.bittreasure.v1.coin.controller.facade.CoinControllerFacade;
import br.com.bittreasure.v1.coin.models.Coin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/teste")
public class CoinController {

    private CoinControllerFacade coinControllerFacade;

    public CoinController(CoinControllerFacade coinControllerFacade) {
        this.coinControllerFacade = coinControllerFacade;
    }

    @GetMapping
    public Coin teste() {
        return coinControllerFacade.run();
    }

    @GetMapping("/{id}")
    public Coin find(@PathVariable("id")String id) {
        return coinControllerFacade.find(id);
    }

    @GetMapping("/todos")
    public List<Coin> findAll() {
        return coinControllerFacade.findAll();
    }

    @GetMapping("/falso")
    public Coin falso() {
        return coinControllerFacade.salvaFalso();
    }
}
