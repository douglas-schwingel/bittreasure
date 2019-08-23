package br.com.bittreasure.contract.v1.coin.controller.facade;

import br.com.bittreasure.contract.v1.coin.mapper.CoinMapper;
import br.com.bittreasure.contract.v1.coin.models.response.CoinExchangesResponse;
import br.com.bittreasure.contract.v1.coin.models.response.CoinResponse;
import br.com.bittreasure.contract.v1.coin.models.response.ListCompleteCoinResponse;
import br.com.bittreasure.contract.v1.coin.models.response.ListSimplifiedCoinResponse;
import br.com.bittreasure.impl.coin.facades.CoinFacadeImpl;
import br.com.bittreasure.impl.coin.models.Coin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CoinControllerFacade {

    private final CoinFacadeImpl facadeImpl;

    public CoinResponse find(String id) {
        return CoinMapper.mapToCoinResponse(facadeImpl.find(id));
    }

    public ListSimplifiedCoinResponse findAll(@Nullable String filter, @Nullable String value) {
        List<Coin> list = facadeImpl.findAll(filter, value);
        return CoinMapper.mapToListSimplifiedCoinResponse(list);
    }

    public ListCompleteCoinResponse findAllComplete() {
        List<Coin> all = facadeImpl.findAll();
        return CoinMapper.mapToListCompleteCoinResponse(all);
    }

    public CoinExchangesResponse findExchanges(String id) {
        return CoinMapper.mapToCoinExchangesResponse(facadeImpl.findCoinExchanges(id));
    }
}
