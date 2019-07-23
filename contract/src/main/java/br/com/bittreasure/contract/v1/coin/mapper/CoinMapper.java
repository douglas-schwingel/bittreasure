package br.com.bittreasure.contract.v1.coin.mapper;

import br.com.bittreasure.contract.v1.coin.models.response.CompleteCoinResponse;
import br.com.bittreasure.contract.v1.coin.models.response.ListSimplifiedCoinResponse;
import br.com.bittreasure.contract.v1.coin.models.response.SimplifiedCoinResponse;
import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.contract.v1.coin.models.response.CoinResponse;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .isNew(coin.getIsNew())
                .isActive(coin.getIsActive())
                .type(coin.getType())
                .build();
    }

    public SimplifiedCoinResponse mapToSimplifiedCoinResponse(Coin coin) {
        return SimplifiedCoinResponse.builder()
                .id(coin.getId())
                .name(coin.getName())
                .isActive(coin.getIsActive())
                .rank(coin.getRank())
                .lastDataAt(coin.getLastDataAt())
                .build();
    }

    public ListSimplifiedCoinResponse mapToListSimplifiedCoinResponse(List<Coin> list) {
        var builder = ListSimplifiedCoinResponse.builder();
        list.forEach(c -> builder.coin(mapToSimplifiedCoinResponse(c)));
        return builder.build();
    }
}
