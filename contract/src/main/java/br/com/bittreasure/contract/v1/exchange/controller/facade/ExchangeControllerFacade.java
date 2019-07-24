package br.com.bittreasure.contract.v1.exchange.controller.facade;

import br.com.bittreasure.contract.v1.exchange.mapper.ExchangeMapper;
import br.com.bittreasure.contract.v1.exchange.models.response.ExchangeResponse;
import br.com.bittreasure.contract.v1.exchange.models.response.ListExchangeResponse;
import br.com.bittreasure.impl.exchange.facades.ExchangeFacadeImpl;
import org.springframework.stereotype.Service;

@Service
public class ExchangeControllerFacade {

    private final ExchangeFacadeImpl facade;
    private ExchangeMapper mapper;

    public ExchangeControllerFacade(ExchangeFacadeImpl facade, ExchangeMapper mapper) {
        this.facade = facade;
        this.mapper = mapper;
    }

    public ExchangeResponse find(String id) {
        return mapper.mapToExchangeResponse(facade.find(id));
    }

    public ListExchangeResponse saveAll() {
        return mapper.mapToListExchangeResponse(facade.saveAll());
    }

    public ListExchangeResponse findAll() {
        return mapper.mapToListExchangeResponse(facade.findAll());
    }
}
