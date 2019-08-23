package br.com.bittreasure.contract.v1.exchange.mapper;

import br.com.bittreasure.contract.v1.exchange.models.response.ExchangeResponse;
import br.com.bittreasure.contract.v1.exchange.models.response.ListExchangeResponse;
import br.com.bittreasure.impl.exchange.models.Exchange;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeMapper {

    public static ExchangeResponse mapToExchangeResponse(Exchange exchange) {
        return ExchangeResponse.builder()
                .id(exchange.getId())
                .name(exchange.getName())
                .active(exchange.getActive())
                .description(exchange.getDescription())
                .markets(exchange.getAllMarkets())
                .build();
    }

    public static ListExchangeResponse mapToListExchangeResponse(List<Exchange> exchanges) {
        var builder = ListExchangeResponse.builder();
        exchanges.forEach(e -> builder.exchange(mapToExchangeResponse(e)));
        return builder.build();
    }
}
