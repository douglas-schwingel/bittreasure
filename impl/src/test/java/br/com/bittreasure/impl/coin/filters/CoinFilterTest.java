package br.com.bittreasure.impl.coin.filters;

import br.com.bittreasure.impl.coin.filters.models.FilterByRankGreater;
import br.com.bittreasure.impl.coin.filters.models.FilterType;
import br.com.bittreasure.impl.coin.filters.models.NoFilter;
import br.com.bittreasure.impl.exceptions.ApiException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CoinFilterTest {

    private CoinFilter coinFilter;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        coinFilter = spy(CoinFilter.class);
    }

    @Test
    public void mustReturnAValidCoinFilter() {
        FilterType rank_greater = coinFilter.filtra("rank_greater");
        verify(coinFilter, times(1)).filtra("rank_greater");
        assertEquals(FilterByRankGreater.class, rank_greater.getClass());
    }

    @Test
    public void shouldThrowApiException() {
        exception.expect(ApiException.class);
        exception.expectMessage("Invalid filter");
        coinFilter.filtra("not_existing");
    }

    @Test
    public void shouldReturnNoFilterClass() {
        String filter = null;
        FilterType filtra = coinFilter.filtra(filter);

        assertEquals(NoFilter.class, filtra.getClass());
    }

}