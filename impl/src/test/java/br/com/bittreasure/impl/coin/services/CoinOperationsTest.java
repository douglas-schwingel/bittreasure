package br.com.bittreasure.impl.coin.services;

import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.exceptions.ApiException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class CoinOperationsTest {

    private CoinOperations coinOperations;
    private Environment environment;
    private RestTemplate template;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        environment = mock(Environment.class);
        template = spy(RestTemplate.class);
        coinOperations = new CoinOperations(environment);
    }

    @Test
    public void shouldGetTheListOfCoinsFromCoinpaprika() {
        when(environment.getProperty("api.getCoins")).thenReturn("https://api.coinpaprika.com/v1/coins");
        coinOperations.setApiUri();
        List<Coin> coins = coinOperations.getCoins(template);
        assertFalse(coins.isEmpty());
    }

    @Test
    public void shoudThrowHttpClientErrorException() {
        exception.expect(ApiException.class);
        exception.expectMessage("Too many requests to Coinpaprika. Limit: 10/sec");
        when(environment.getProperty("api.getCoins")).thenReturn("https://api.coinpaprika.com/v1/coins");
        coinOperations.setApiUri();
        when(template.exchange("https://api.coinpaprika.com/v1/coins", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Coin>>() {
                })).thenThrow(new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS));
        coinOperations.getCoins(template);

    }


}