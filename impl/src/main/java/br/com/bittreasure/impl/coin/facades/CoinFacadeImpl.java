package br.com.bittreasure.impl.coin.facades;

import br.com.bittreasure.impl.coin.filters.CoinFilter;
import br.com.bittreasure.impl.coin.filters.models.FilterType;
import br.com.bittreasure.impl.coin.filters.models.NoFilter;
import br.com.bittreasure.impl.coin.services.CoinService;
import br.com.bittreasure.impl.coin.models.Coin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoinFacadeImpl {

    private final CoinService service;
    private final CoinFilter coinFilter;

    public Coin find(String id) {
        return service.find(id);
    }

    public List<Coin> findAll(@Nullable String filter, @Nullable String value) {
        FilterType filterType = coinFilter.filtra(filter);
        return service.findWithFilters(filterType, value);
    }

    public List<Coin> findAll() {
        return service.findWithFilters(new NoFilter(), "");
    }


    public Coin findCoinExchanges(String id) {
        return service.find(id);
    }
}
