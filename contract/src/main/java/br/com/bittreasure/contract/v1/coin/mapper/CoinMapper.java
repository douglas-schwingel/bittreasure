package br.com.bittreasure.contract.v1.coin.mapper;

import br.com.bittreasure.contract.v1.coin.models.response.*;
import br.com.bittreasure.impl.coin.models.Coin;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CoinMapper {

    public static CoinResponse mapToCoinResponse(Coin coin) {
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

    public static SimplifiedCoinResponse mapToSimplifiedCoinResponse(Coin coin) {
        return SimplifiedCoinResponse.builder()
                .id(coin.getId())
                .name(coin.getName())
                .isActive(coin.getIsActive())
                .rank(coin.getRank())
                .lastDataAt(coin.getLastDataAt())
                .build();
    }

    public static ListSimplifiedCoinResponse mapToListSimplifiedCoinResponse(List<Coin> list) {
        var builder = ListSimplifiedCoinResponse.builder();
        list.forEach(c -> builder.coin(mapToSimplifiedCoinResponse(c)));
        return builder.build();
    }

    public static ListCompleteCoinResponse mapToListCompleteCoinResponse(List<Coin> all) {
        return ListCompleteCoinResponse.builder().coins(all).build();
    }

    public static CoinExchangesResponse mapToCoinExchangesResponse(Coin coin) {
        return CoinExchangesResponse.builder()
                .id(coin.getId())
                .name(coin.getName())
                .exchanges(coin.getExchanges().stream()
                        .sorted(String::compareTo).collect(Collectors.toCollection(LinkedHashSet::new))
                )
                .build();
    }
}
