package br.com.bittreasure.contract.v1.exchange.models.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class ExchangeResponse {


    private String id;
    @ToString.Include
    private String name;
    @ToString.Include
    private String description;
    private String active;
}
