package br.com.bittreasure.contract.v1.coin.models.response;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.Set;

@Builder
@Data
public class CoinExchangesResponse {

    private String id;
    private String name;
    @Singular
    private Set<String> exchanges;
}
