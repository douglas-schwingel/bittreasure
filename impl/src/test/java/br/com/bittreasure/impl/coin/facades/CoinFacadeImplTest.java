package br.com.bittreasure.impl.coin.facades;

import br.com.bittreasure.impl.coin.filters.CoinFilter;
import br.com.bittreasure.impl.coin.filters.models.FilterByRankGreater;
import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.repositories.CoinRepository;
import br.com.bittreasure.impl.coin.services.CoinOperations;
import br.com.bittreasure.impl.coin.services.CoinService;
import br.com.bittreasure.impl.coin.test.CoinTestsUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CoinFacadeImplTest {

    private CoinFilter coinFilter;
    private CoinService service;
    private CoinRepository repository;
    private CoinOperations operations;
    private CoinTestsUtils utils = new CoinTestsUtils();
    private CoinFacadeImpl facade;

    @Before
    public void setUp() {
        coinFilter = mock(CoinFilter.class);
        repository = mock(CoinRepository.class);
        operations = mock(CoinOperations.class);
        service = spy(new CoinService(repository, operations));
        facade = new CoinFacadeImpl(service, coinFilter);
    }

    @Test
    public void shouldReturnTheProperCoinFromService() {
        Coin bitcoin = utils.getCoin("btc-bitcoin");
        when(repository.findById("btc-bitcoin")).thenReturn(Optional.of(bitcoin));
        Coin coin = facade.find("btc-bitcoin");

        assertEquals(bitcoin, coin);
        verify(service, times(1)).find("btc-bitcoin");
    }

    @Test
    public void shouldFindAllTheCoinsInTheRepository() {
        List<Coin> coins = utils.getCoins();
        when(repository.findAll()).thenReturn(coins);

        List<Coin> all = facade.findAll();

        assertEquals(coins.size(), all.size());
        assertEquals(coins.get(0), all.get(0));
    }

    @Test
    public void shouldFindAllWithFilters() {
        List<Coin> filtered = utils.getCoins().stream()
                .filter(c -> c.getRank() > 10)
                .sorted(Comparator.comparingInt(Coin::getRank))
                .collect(Collectors.toList());

        when(coinFilter.filtra("rank_greater")).thenReturn(new FilterByRankGreater());
        when(repository.findAllByRankGreaterThanOrderByRank(10)).thenReturn(filtered);

        List<Coin> all = facade.findAll("rank_greater", "10");

        assertEquals(filtered.size(), all.size());
        assertEquals(filtered.get(1), all.get(1));


    }
}