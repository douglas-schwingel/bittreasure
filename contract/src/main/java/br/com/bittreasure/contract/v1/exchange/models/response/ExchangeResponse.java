package br.com.bittreasure.contract.v1.exchange.models.response;

import br.com.bittreasure.impl.exchange.models.Market;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
public class ExchangeResponse {

    private String id;
    @ToString.Include
    private String name;
    @ToString.Include
    private String description;
    private String active;
    private List<Market> markets;
}
