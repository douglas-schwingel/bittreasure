package br.com.bittreasure.impl.exchange.facades;

import br.com.bittreasure.impl.coin.services.CoinService;
import br.com.bittreasure.impl.exchange.models.Exchange;
import br.com.bittreasure.impl.exchange.services.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeFacadeImpl {

    private final ExchangeService service;
    private final CoinService coinService;

    public Exchange find(String id) {
        return service.find(id);
    }

    public List<Exchange> findAll() {
        return service.findAll();
    }

    public List<Exchange> saveAll() {
        List<Exchange> exchanges = service.getExchangesInformation();
        service.updateMarkets(exchanges, coinService);
        return exchanges;
    }


}
