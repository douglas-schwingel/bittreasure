package br.com.bittreasure.impl.coin.exception;

public class CoinNotFoundException extends RuntimeException {
    public CoinNotFoundException(String s) {
        super(s);
    }
}
