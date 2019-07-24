package br.com.bittreasure.impl.coin.services;

import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.services.CoinOperations;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class CoinOperationsTest {

    private CoinOperations coinOperations;

    @Before
    public void setUp() {
        coinOperations = new CoinOperations();
    }

    @Test
    public void shouldGetTheListOfCoinsFromCoinpaprika() {
        List<Coin> coins = coinOperations.getCoins();
        assertFalse(coins.isEmpty());
    }


}