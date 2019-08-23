package br.com.bittreasure.contract.v1.exchange.controller.facade;

import br.com.bittreasure.contract.v1.exchange.mapper.ExchangeMapper;
import br.com.bittreasure.contract.v1.exchange.models.response.ExchangeResponse;
import br.com.bittreasure.contract.v1.exchange.models.response.ListExchangeResponse;
import br.com.bittreasure.impl.exchange.facades.ExchangeFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeControllerFacade {

    private final ExchangeFacadeImpl facade;

    public ExchangeResponse find(String id) {
        return ExchangeMapper.mapToExchangeResponse(facade.find(id));
    }

    public ListExchangeResponse saveAll() {
        return ExchangeMapper.mapToListExchangeResponse(facade.saveAll());
    }

    public ListExchangeResponse findAll() {
        return ExchangeMapper.mapToListExchangeResponse(facade.findAll());
    }
}
