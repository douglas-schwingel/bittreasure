package br.com.bittreasure.impl.coin.services;

import br.com.bittreasure.impl.coin.models.Coin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class CoinOperations {

    public List<Coin> getCoins() {
        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<List<Coin>> responseEntity = template.exchange("https://api.coinpaprika.com/v1/coins", HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Coin>>() {
                    });
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            log.warn("Get coins: {}", e.getMessage());
            return getCoins();
        }

    }

    public Coin getCoinInformation(String id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Coin> responseEntity;
        try {
            responseEntity = template.exchange("https://api.coinpaprika.com/v1/coins/" + id, HttpMethod.GET,
                    null, new ParameterizedTypeReference<Coin>() {
                    });
        } catch (HttpClientErrorException e) {
            log.warn("Get information: {}", e.getMessage());
            return getCoinInformation(id);
        }
        return responseEntity.getBody();
    }

    public void setPriceInformations(Coin coin) {
        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<Coin[]> responseEntity = template.exchange(
                    "https://api.coinpaprika.com/v1/coins/"+ coin.getId() + "/ohlcv/latest/", HttpMethod.GET,
                    null, new ParameterizedTypeReference<Coin[]>() {
                    });
            Coin body = responseEntity.getBody()[0];
            setCoinInformations(coin, body);
        } catch (IndexOutOfBoundsException e) {
            log.warn("Error trying to add price to {}: Coin doesn't have a price", coin.getId());
        } catch (HttpClientErrorException e) {
            log.warn("Get price: {}", e.getMessage());
            setPriceInformations(coin);
        }
    }

    private void setCoinInformations(Coin coin, Coin body) {
        coin.setTimeOpen(body.getTimeOpen());
        coin.setTimeClose(body.getTimeClose());
        coin.setOpen(body.getOpen());
        coin.setClose(body.getClose());
        coin.setHigh(body.getHigh());
        coin.setLow(body.getLow());
        coin.setVolume(body.getVolume());
        coin.setMarketCap(body.getMarketCap());
    }
}
