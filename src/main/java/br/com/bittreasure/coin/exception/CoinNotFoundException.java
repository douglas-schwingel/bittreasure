package br.com.bittreasure.coin.exception;

public class CoinNotFoundException extends RuntimeException {
    public CoinNotFoundException(String s) {
        super(s);
    }
}
