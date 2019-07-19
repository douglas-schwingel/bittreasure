package br.com.bittreasure.impl.coin.filters.models;

import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.repositories.CoinRepository;

import java.util.List;

public interface FilterType {
    List<Coin> doSearch(CoinRepository repository, String value);
}
