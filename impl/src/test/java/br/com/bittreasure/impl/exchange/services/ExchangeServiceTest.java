package br.com.bittreasure.impl.exchange.services;

import br.com.bittreasure.impl.exchange.repositories.ExchangeRepository;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class ExchangeServiceTest {

    private ExchangeRepository exchangeRepository;
    private ExchangeService service;

    @Before
    public void setUp() {
        exchangeRepository = mock(ExchangeRepository.class);
        service = new ExchangeService(exchangeRepository);
    }


}