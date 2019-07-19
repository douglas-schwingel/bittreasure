package br.com.bittreasure.impl.coin.filters.models;

import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.repositories.CoinRepository;

import java.util.List;

public class FilterByGreaterRank implements FilterType {
    @Override
    public List<Coin> doSearch(CoinRepository repository, String value) {
        return repository.findAllByRankGreaterThan(Integer.valueOf(value));
    }
}
