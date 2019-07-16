package br.com.bittreasure.coin;

import br.com.bittreasure.coin.models.Coin;
import br.com.bittreasure.teste.Teste;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/teste")
public class CoinController {

    private Teste teste;

    public CoinController(Teste teste) {
        this.teste = teste;
    }

    @GetMapping
    public Coin teste() {
        return teste.run();
    }
}
