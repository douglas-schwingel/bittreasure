package br.com.bittreasure;

import br.com.bittreasure.coin.repositories.CoinRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@SpringBootApplication
public class BitTreasureApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitTreasureApplication.class, args);
	}

}
