package br.com.bittreasure.contract.v1.exchange.models.response;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Builder
@Data
public class ExchangeCompleteResponse {

    @Singular
    private List<ExchangeResponse> exchanges;
}
