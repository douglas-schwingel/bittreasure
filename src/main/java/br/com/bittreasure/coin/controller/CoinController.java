package br.com.bittreasure.coin.controller;

import br.com.bittreasure.coin.models.Coin;
import br.com.bittreasure.teste.Teste;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoinController {

    private Teste teste;

    public CoinController(Teste teste) {
        this.teste = teste;
    }

    @GetMapping("/teste")
    public Coin teste() {
        return teste.run();
    }

    @GetMapping("/teste/{id}")
    public Coin find(@PathVariable("id")String id) {
        return teste.find(id);
    }

    @GetMapping("/teste/todos")
    public List<Coin> findAll() {
        return teste.findAll();
    }
}
