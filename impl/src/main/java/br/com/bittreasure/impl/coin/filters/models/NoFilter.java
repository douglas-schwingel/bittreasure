package br.com.bittreasure.impl.coin.filters.models;

import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.repositories.CoinRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NoFilter implements FilterType {
    @Override
    public List<Coin> doSearch(CoinRepository repository, String value) {
        List<Coin> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        log.info("List size: {}", list.size());
        return list;
    }
}
