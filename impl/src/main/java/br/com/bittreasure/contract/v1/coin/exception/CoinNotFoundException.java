package br.com.bittreasure.contract.v1.coin.exception;

public class CoinNotFoundException extends RuntimeException {
    public CoinNotFoundException(String s) {
        super(s);
    }
}
