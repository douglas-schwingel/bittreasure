package br.com.bittreasure.contract.v1.coin.controller.facade;

import br.com.bittreasure.contract.v1.coin.facades.CoinFacadeImpl;
import br.com.bittreasure.contract.v1.coin.mapper.CoinMapper;
import br.com.bittreasure.contract.v1.coin.models.response.CoinResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CoinControllerFacade {

    private CoinFacadeImpl facadeImpl;
    private CoinMapper mapper;

    public CoinControllerFacade(CoinFacadeImpl facadeImpl, CoinMapper mapper) {
        this.facadeImpl = facadeImpl;
        this.mapper = mapper;
    }


    public CoinResponse find(String id, String filter, String value) {
        return mapper.mapToCoinResponse(facadeImpl.find(id, filter, value));
    }

}