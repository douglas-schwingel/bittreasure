package br.com.bittreasure.impl.coin.services;

import br.com.bittreasure.impl.coin.filters.models.FilterByRankGreater;
import br.com.bittreasure.impl.coin.filters.models.FilterByRankLess;
import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.repositories.CoinRepository;
import br.com.bittreasure.impl.coin.test.CoinTestsUtils;
import br.com.bittreasure.impl.exceptions.ApiException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CoinServiceTest {

    private CoinRepository repository;
    private CoinService service;
    private CoinOperations operations;
    private CoinTestsUtils utils;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        repository = spy(CoinRepository.class);
        Environment environment = mock(Environment.class);
        when(environment.getProperty("api.getCoins")).thenReturn("https://api.coinpaprika.com/v1/coins");
        operations = mock(CoinOperations.class);
        utils = new CoinTestsUtils();
        service = new CoinService(repository, operations);
    }

    @Test
    public void shouldReturnTheRightCoin() {
        Coin coinUtil = utils.getCoin("btc-bitcoin");
        when(repository.findById("btc-bitcoin")).thenReturn(Optional.of(coinUtil));
        Coin coin = service.find("btc-bitcoin");

        assertEquals(coinUtil, coin);
    }

    @Test
    public void shouldReturnOnlyCoinsWithRankGreaterThan10() {
        List<Coin> filteredCoins = utils.getCoins().stream()
                .filter(c -> c.getRank() > 10)
                .sorted(Comparator.comparingInt(Coin::getRank))
                .collect(Collectors.toList());

        when(repository.findAllByRankGreaterThanOrderByRank(10)).thenReturn(filteredCoins);
        List<Coin> response = service.findWithFilters(new FilterByRankGreater(), "10");
        assertEquals(2, response.size());
        assertEquals("-money", response.get(1).getId());
    }

    @Test
    public void shouldReturnOnlyCoinsWithRankLessThen5() {
        List<Coin> filteredCoins = utils.getCoins().stream()
                .filter(c -> c.getRank() < 10)
                .sorted(Comparator.comparingInt(Coin::getRank))
                .collect(Collectors.toList());
        when(repository.findAllByRankLessThanOrderByRank(10)).thenReturn(filteredCoins);
        List<Coin> response = service.findWithFilters(new FilterByRankLess(), "10");

        assertEquals(2, response.size());
        assertEquals("btc-bitcoin", response.get(0).getId());
    }

    @Test
    public void shouldThrowApiExceptionOnMethodFind() {
        expectedException.expect(ApiException.class);
        expectedException.expectMessage("No coin with this id");
        service.find("any");
    }

    @Test
    public void shouldGetTheResponseFromCoinpaprika() {
        List<Coin> list = utils.getCoins();
        when(operations.getCoins(any(RestTemplate.class))).thenReturn(list);
        list.forEach(c -> when(operations.getCoinInformation(eq(c.getId()), any())).thenReturn(c));

        await().until(service::save, coins -> coins.size() == 4);

        verify(operations, times(1)).getCoins(any());
        verify(operations, times(4)).getCoinInformation(anyString(), any(RestTemplate.class));
    }

}