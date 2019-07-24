package br.com.bittreasure.impl.coin.test;

import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.models.Tag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CoinTestsUtils {

    private List<Coin> coins;

    public CoinTestsUtils() {
        Coin money = Coin.builder()
                .name("Money").id("-money").rank(8000).hardwareWallet("false").hashAlgorithm("Unknown")
                .symbol("$$$").firstDataAt(LocalDateTime.MIN).proofType("Proof of Work & Proof of Stake")
                .description("Money is a digital currency secured by cryptography. As a descendant of Bitcoin it shares " +
                        "many similarities but is more advanced and business friendly.")
                .lastDataAt(LocalDateTime.now())
                .isNew("true").isActive("true").team(new ArrayList<>()).type("br/com/bittreasure/impl/coin")
                .tag(new Tag("test", "Cryptocurrency", "5", "6"))
                .orgStructure("Descentralized").build();

        Coin ethereum = Coin.builder()
                .name("Ethereum").id("eth-ethereum").rank(2).hardwareWallet("true").hashAlgorithm("Ethash")
                .symbol("ETH").firstDataAt(LocalDateTime.now()).proofType("Ethereum consensus")
                .description("Ethereum is a decentralized platform for applications. Applications build on it can use " +
                        "smart contracts - computer algorithms which execute themselves when data is supplied to the " +
                        "platform. There is no need for any human operators.")
                .startedAt(LocalDateTime.parse("2015-08-07T00:00:00")).timeOpen(LocalDateTime.parse("2019-07-22T00:00:00"))
                .timeClose(LocalDateTime.parse("2019-07-22T23:59:59")).open(225.81).high(227.10).low(213.45)
                .volume(5912376134L).marketCap(24161224646L).close(217.45)
                .exchanges(Set.of("bgogo", "binance", "bitbank"))
                .lastDataAt(LocalDateTime.now()).lastDataAt(LocalDateTime.parse("2019-07-23T12:41:49"))
                .isNew("false").isActive("true").team(new ArrayList<>()).type("br/com/bittreasure/impl/coin")
                .tag(new Tag("test", "Cryptocurrency", "5", "6"))
                .orgStructure("Semi-Centralized").build();

        Coin bitcoin = Coin.builder()
                .name("Bitcoin").id("btc-bitcoin").rank(1).hardwareWallet("true").hashAlgorithm("SHA256")
                .symbol("BTC").firstDataAt(LocalDateTime.parse("2013-04-28T00:00:00")).proofType("Proof of Work")
                .description("Bitcoin is a cryptocurrency and worldwide payment system. It is the first decentralized " +
                        "digital currency, as the system works without a central bank or single administrator.")
                .startedAt(LocalDateTime.parse("2015-08-07T00:00:00")).timeOpen(LocalDateTime.parse("2019-07-22T00:00:00"))
                .timeClose(LocalDateTime.parse("2019-07-22T23:59:59")).open(10598.94).high(10655.74).low(10158.15)
                .volume(14307000276L).marketCap(188984700811L).close(217.45)
                .exchanges(Set.of("btc-markets","binance", "bitbank"))
                .lastDataAt(LocalDateTime.now()).lastDataAt(LocalDateTime.parse("2019-07-23T12:41:49"))
                .isNew("false").isActive("true").team(new ArrayList<>()).type("br/com/bittreasure/impl/coin")
                .tag(new Tag("test", "Cryptocurrency", "5", "6"))
                .orgStructure("Semi-Centralized").build();

        Coin testCoin = Coin.builder()
                .name("TestCoin").id("tst-tstcoin").rank(42).hardwareWallet("true").hashAlgorithm("Test1")
                .symbol("TST").firstDataAt(LocalDateTime.parse("2013-04-28T00:00:00")).proofType("Proof of Work")
                .description("Just a test coin. It's not valid in any national territory. Do not buy drugs")
                .startedAt(LocalDateTime.parse("2015-08-07T00:00:00")).timeOpen(LocalDateTime.parse("2019-07-22T00:00:00"))
                .timeClose(LocalDateTime.parse("2019-07-22T23:59:59")).open(10598.94).high(10655.74).low(10158.15)
                .volume(14307000276L).marketCap(188984700811L).close(217.45)
                .exchanges(Set.of("btc-markets","binance", "bitbank"))
                .lastDataAt(LocalDateTime.now()).lastDataAt(LocalDateTime.parse("2019-07-23T12:41:49"))
                .isNew("false").isActive("true").team(new ArrayList<>()).type("br/com/bittreasure/impl/coin")
                .tag(new Tag("test", "Cryptocurrency", "5", "6"))
                .orgStructure("Semi-Centralized").build();
        coins = Arrays.asList(money, ethereum, bitcoin, testCoin);
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public Coin getCoin(String id) {
        for (Coin coin : coins) {
            if (coin.getId().equals(id)) return coin;
        }
        return null;
    }
}
