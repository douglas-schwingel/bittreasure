package br.com.bittreasure.contract.v1.coin.models.response;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class ListSimplifiedCoinResponse {

    @Singular
    private List<SimplifiedCoinResponse> coins;
}
