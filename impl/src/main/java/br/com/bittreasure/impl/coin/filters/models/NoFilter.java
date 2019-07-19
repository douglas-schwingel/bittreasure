package br.com.bittreasure.impl.coin.filters.models;

import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.repositories.CoinRepository;

import java.util.ArrayList;
import java.util.List;

public class NoFilter implements FilterType {
    @Override
    public List<Coin> doSearch(CoinRepository repository, String value) {
        List<Coin> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }
}
