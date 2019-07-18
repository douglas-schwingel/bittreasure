package br.com.bittreasure.contract.v1.coin.controller.facade;

import br.com.bittreasure.contract.v1.coin.mapper.CoinMapper;
import br.com.bittreasure.contract.v1.coin.models.response.CoinResponse;
import br.com.bittreasure.contract.v1.coin.models.response.ListSimplifiedCoinResponse;
import br.com.bittreasure.impl.coin.facades.CoinFacadeImpl;
import br.com.bittreasure.impl.coin.models.Coin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoinControllerFacade {

    private CoinFacadeImpl facadeImpl;
    private CoinMapper mapper;

    public CoinControllerFacade(CoinFacadeImpl facadeImpl, CoinMapper mapper) {
        this.facadeImpl = facadeImpl;
        this.mapper = mapper;
    }


    public CoinResponse find(String id, @Nullable String filter, @Nullable  String value) {
        return mapper.mapToCoinResponse(facadeImpl.find(id, filter, value));
    }

    public ListSimplifiedCoinResponse saveAll() {
        List<Coin> list = facadeImpl.saveAll();
        return mapper.mapToListSimplifiedCoinResponse(list);
    }
}
