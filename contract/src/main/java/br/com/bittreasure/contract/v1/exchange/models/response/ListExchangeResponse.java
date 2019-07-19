package br.com.bittreasure.contract.v1.exchange.models.response;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class ListExchangeResponse {

    @Singular
    private List<ExchangeResponse> exchanges;
}
