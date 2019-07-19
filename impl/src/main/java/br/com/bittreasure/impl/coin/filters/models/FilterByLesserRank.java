package br.com.bittreasure.impl.coin.filters.models;

import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.repositories.CoinRepository;

import java.util.List;

public class FilterByLesserRank implements FilterType {

    @Override
    public List<Coin> doSearch(CoinRepository repository, String rank) {
        return repository.findAllByRankLessThan(Integer.valueOf(rank));
    }
}
