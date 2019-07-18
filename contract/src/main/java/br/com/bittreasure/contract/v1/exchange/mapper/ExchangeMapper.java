package br.com.bittreasure.contract.v1.exchange.mapper;

import br.com.bittreasure.contract.v1.exchange.models.response.ExchangeCompleteResponse;
import br.com.bittreasure.contract.v1.exchange.models.response.ExchangeResponse;
import br.com.bittreasure.impl.exchange.models.Exchange;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeMapper {

    public ExchangeResponse mapToExchangeResponse(Exchange exchange) {
        return ExchangeResponse.builder()
                .id(exchange.getId())
                .name(exchange.getName())
                .active(exchange.getActive())
                .description(exchange.getDescription())
                .build();
    }

    public ExchangeCompleteResponse mapToExchangeCompleteResponse(List<Exchange> exchanges) {
        ExchangeCompleteResponse.ExchangeCompleteResponseBuilder builder = ExchangeCompleteResponse.builder();
        exchanges.forEach(e -> builder.exchange(mapToExchangeResponse(e)));
        return builder.build();
    }
}
