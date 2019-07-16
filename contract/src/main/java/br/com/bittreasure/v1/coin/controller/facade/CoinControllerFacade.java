package br.com.bittreasure.v1.coin.controller.facade;

import br.com.bittreasure.v1.coin.facades.CoinFacade;
import br.com.bittreasure.v1.coin.models.Coin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoinControllerFacade {

    private CoinFacade facade;

    public CoinControllerFacade(CoinFacade facade) {
        this.facade = facade;
    }

    public Coin run() {
        return facade.save();
    }

    public Coin find(String id) {
        return facade.find(id);
    }

    public List<Coin> findAll() {
        log.info("Entrou no findAll do ControllerFacade");
        return facade.findAll();
    }

    public Coin salvaFalso(){
        return facade.salvaFalso();
    }

}
