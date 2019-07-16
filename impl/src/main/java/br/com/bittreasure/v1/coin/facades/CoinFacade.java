package br.com.bittreasure.v1.coin.facades;

import br.com.bittreasure.v1.coin.models.Coin;
import br.com.bittreasure.v1.coin.services.CoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoinFacade {

    private CoinService service;

    public CoinFacade(CoinService service) {
        this.service = service;
    }

    public Coin save() {
        return service.save();
    }

    public Coin find(String id) {
        return service.find(id);
    }

    public List<Coin> findAll() {
        log.info("Entrou no findAll do CoinFacade");
        return service.findAll();
    }

    public Coin salvaFalso(){
        return service.teste();
    }

}
