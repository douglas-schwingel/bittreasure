package br.com.bittreasure.contract.v1.coin.controller.facade;

import br.com.bittreasure.contract.v1.coin.facades.CoinFacade;
import br.com.bittreasure.contract.v1.coin.mapper.CoinMapper;
import br.com.bittreasure.contract.v1.coin.models.response.CoinResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CoinControllerFacade {

    private CoinFacade facade;
    private CoinMapper mapper;

    public CoinControllerFacade(CoinFacade facade, CoinMapper mapper) {
        this.facade = facade;
        this.mapper = mapper;
    }


    public CoinResponse find(String id, String filter, String value) {
        return mapper.mapToCoinResponse(facade.find(id));
    }

}
