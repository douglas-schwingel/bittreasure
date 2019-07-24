package br.com.bittreasure.contract.v1.coin.models.response;

import br.com.bittreasure.impl.coin.models.Coin;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListCompleteCoinResponse {

    private List<Coin> coins;
}
