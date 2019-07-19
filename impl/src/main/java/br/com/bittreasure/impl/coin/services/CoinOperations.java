package br.com.bittreasure.impl.coin.services;

import br.com.bittreasure.impl.coin.models.Coin;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

class CoinOperations {

    static List<Coin> getCoins() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Coin>> responseEntity = template.exchange("https://api.coinpaprika.com/v1/coins", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Coin>>() {
                });
        return responseEntity.getBody();
    }

    static Coin getAllCoinInformations(Coin coin) {
        Coin coinInformation = getCoinInformation(coin.getId());
        return getPrice(coinInformation);
    }

    private static Coin getCoinInformation(String id) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Coin> responseEntity = template.exchange("https://api.coinpaprika.com/v1/coins/" + id, HttpMethod.GET,
                null, new ParameterizedTypeReference<Coin>() {
                });
        return responseEntity.getBody();
    }

    private static Coin getPrice(Coin coin) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Coin[]> responseEntity = template.exchange(
                "https://api.coinpaprika.com/v1/coins/"+ coin.getId() + "/ohlcv/latest/", HttpMethod.GET,
                null, new ParameterizedTypeReference<Coin[]>() {
                });
        Coin body = responseEntity.getBody()[0];
        coin.setTimeOpen(body.getTimeOpen());
        coin.setTimeClose(body.getTimeClose());
        coin.setOpen(body.getOpen());
        coin.setClose(body.getClose());
        coin.setHigh(body.getHigh());
        coin.setLow(body.getLow());
        coin.setVolume(body.getVolume());
        coin.setMarketCap(body.getMarketCap());
        return coin;
    }
}
