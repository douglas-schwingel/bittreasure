package br.com.bittreasure.contract.v1.coin.facades;

import br.com.bittreasure.contract.v1.coin.filters.CoinFilter;
import br.com.bittreasure.contract.v1.coin.services.CoinService;
import br.com.bittreasure.contract.v1.coin.models.Coin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoinFacadeImpl {

    private CoinService service;
    private final CoinFilter coinFilter;

    public CoinFacadeImpl(CoinService service, CoinFilter filter) {
        this.service = service;
        this.coinFilter = filter;
    }

    public Coin save() {
        return service.save();
    }

    public Coin find(String id, @Nullable String filter, @Nullable String value) {
        coinFilter.filtra(filter, value);
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
