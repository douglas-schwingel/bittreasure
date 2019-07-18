package br.com.bittreasure.contract.v1.coin.mapper;

import br.com.bittreasure.contract.v1.coin.models.response.CompleteCoinResponse;
import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.contract.v1.coin.models.response.CoinResponse;
import org.springframework.stereotype.Service;

@Service
public class CoinMapper {

    public CompleteCoinResponse mapToCompleteResponse(Coin coin) {
        return null;
    }

    public CoinResponse mapToCoinResponse(Coin coin) {
        return CoinResponse.builder()
                .id(coin.getId())
                .name(coin.getName())
                .symbol(coin.getSymbol())
                .rank(coin.getRank())
                .is_new(coin.getIs_new())
                .is_active(coin.getIs_active())
                .type(coin.getType())
                .build();
    }
}
